package sg.ntu.itcm.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import sg.ntu.itcm.dto.UserDto;
import sg.ntu.itcm.dto.UserInfoDto;
import sg.ntu.itcm.error.UserNotFoundException;
import sg.ntu.itcm.event.OnRegistrationCompleteEvent;
import sg.ntu.itcm.model.EmailVerificationToken;
import sg.ntu.itcm.model.PasswordResetToken;
import sg.ntu.itcm.model.User;
import sg.ntu.itcm.service.EmailService;
import sg.ntu.itcm.service.GenericService;
import sg.ntu.itcm.service.LocalizationMessagesService;
import sg.ntu.itcm.service.SecurityUserService;
import sg.ntu.itcm.service.UserService;
import sg.ntu.itcm.util.GenericResponse;

@Controller
public class UserController {
	
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired  
    private UserService userService;
	
	@Autowired
    private SecurityUserService securityUserService;

    @Autowired
    private LocalizationMessagesService context;
    
    @Autowired
    private GenericService genericService;
    
    @Autowired
	private EmailService emailService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;
    
    // =========================== APIs for website ===========================
    // Registration
    @PostMapping(value = "/user/registration")
    public ResponseEntity<GenericResponse> registerUserAccount(@Valid final UserDto accountDto, final Locale locale, final HttpServletRequest request) {
        log.info("Registering user account with information: {}", accountDto);

        final User registered = userService.registerNewUserAccount(accountDto);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, locale, genericService.getAppUrl(request)));
        GenericResponse responseBody = new GenericResponse("success");
        
        return new ResponseEntity<GenericResponse>(responseBody, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/regitrationConfirm")
    public String confirmRegistration(final Locale locale, final Model model, @RequestParam("token") final String token) {
        final String result = userService.validateVerificationToken(token);
        if (result == null) {
            model.addAttribute("message", context.getMessages().getMessage("message.accountVerified", null, locale));
            return "redirect:/login?lang=" + locale.toString();
        }
        if (result == "expired") {
            model.addAttribute("expired", true);
            model.addAttribute("token", token);
        }
        model.addAttribute("message", context.getMessages().getMessage("auth.message." + result, null, locale));
        return "redirect:/badUser.html?lang=" + locale.toString();
    }

    // user activation - verification
    @GetMapping(value = "/user/resendRegistrationToken")
    public ResponseEntity<GenericResponse> resendRegistrationToken(final Locale locale, final HttpServletRequest request, @RequestParam("token") final String existingToken) {
        
    	final EmailVerificationToken newToken = userService.generateNewVerificationToken(existingToken);
        final User user = userService.getUser(newToken.getEmailVerificationToken());
        emailService.resendUserActivationEmail(user, locale, request, newToken.getEmailVerificationToken());
        GenericResponse responseBody = new GenericResponse(context.getMessages().getMessage("message.resendToken", null, locale));
        return new ResponseEntity<GenericResponse>(responseBody, new HttpHeaders(), HttpStatus.OK);
    }

    // Reset password
    @PostMapping(value = "/user/forgetPassword")
    public ResponseEntity<GenericResponse> resetPassword(final Locale locale, final HttpServletRequest request, @RequestParam("email") final String userEmail) {
        final User user = userService.findUserByEmail(userEmail);
        if (user == null) {
            throw new UserNotFoundException();
        }
        
        if (user.getActivationStatus().equals("NO")) {
        	throw new UserNotFoundException("Unactivated user with email: " + user.getEmail());
        }
        
        final String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = userService.createPasswordResetTokenForUser(user, token);
        emailService.sendResetPasswordEmail(user, locale, request, passwordResetToken);
        GenericResponse responseBody = new GenericResponse(context.getMessages().getMessage("message.forgetPasswordEmail", null, locale));
        return new ResponseEntity<GenericResponse>(responseBody, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping(value = "/backHomepage")
    public String backToHomePage() {
    	final String email = SecurityContextHolder.getContext().getAuthentication().getName();
    	return "redirect:/homepage.html?user=" + email;
    }

    @GetMapping(value = "/user/changePassword")
    public String showChangePasswordPage(final Locale locale, final Model model, @RequestParam("id") final long id, @RequestParam("token") final String token) {
        final String result = securityUserService.validatePasswordResetToken(id, token);
        if (result != null) {
            model.addAttribute("message", context.getMessages().getMessage("auth.message." + result, null, locale));
            return "redirect:/login?lang=" + locale.toString();
        }
        return "redirect:/updatePassword.html?lang=" + locale.toString();
    }

    @PostMapping(value = "/user/savePassword")
    public ResponseEntity<GenericResponse> savePassword(final Locale locale, @RequestParam("password") final String password) {
        final User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.changeUserPassword(user, password);
        GenericResponse responseBody = new GenericResponse(context.getMessages().getMessage("message.updatePasswordSuc", null, locale));
        return new ResponseEntity<GenericResponse>(responseBody, new HttpHeaders(), HttpStatus.OK);
    }

    // change user password
    @PostMapping(value = "/user/updatePassword")
    public ResponseEntity<Object> changeUserPasswordWebsite(final Locale locale, @RequestParam("password") final String password, @RequestParam("oldpassword") final String oldPassword) {
        
    	final User user = userService.changeUserPassword(locale, password, oldPassword);
    	
    	JSONObject data  = new JSONObject();
		data.put("message", context.getMessages().getMessage("message.updatePasswordSuc", null, locale));
		data.put("user", user.getEmail());
    	return new ResponseEntity<Object>(data.toJSONString(), new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping(value = "/user/userAvatar")
    public ResponseEntity<byte[]> getUserAvatar() throws FileNotFoundException, IOException {
        return userService.downloadUserAvatar();
    }
    
    @PostMapping(value = "/user/userAvatarUpload")
    public String doUploadUserAvatar(@RequestParam("file")final MultipartFile file, final Model model, final Locale locale) throws IOException {
    	if (file.isEmpty()) {
	    	model.addAttribute("message", context.getMessages().getMessage("message.uploadFileEmpty", null, locale));
	        return "redirect:/upload.html?lang=" + locale.toString();
	    }
	    
	    if (ImageIO.read(file.getInputStream()) == null) {
	    	model.addAttribute("message", context.getMessages().getMessage("message.wrongFileExtension", null, locale));
	        return "redirect:/upload.html?lang=" + locale.toString();
	    }
	    
	    log.info("Process image file:{}", file.getOriginalFilename());
	    
		final String email = SecurityContextHolder.getContext().getAuthentication().getName();
		final User user = userService.findUserByEmail(email);
	    FileUtils.copyInputStreamToFile(genericService.resizeImage(file), new File(genericService.getUserAvatarPath() + File.separator + user.getUserUuid()));
	    
	    model.addAttribute("message", context.getMessages().getMessage("message.uploadUserAvatar", null, locale));
        return "redirect:/upload.html?lang=" + locale.toString();
    }
    
    @PostMapping(value = "/user/updateUserInfo")
    public ResponseEntity<GenericResponse> doUploadUserAvatar(@Valid final UserInfoDto userInfo, final Locale locale, final HttpServletRequest request) {
    	
    	userService.updateUserInfo(userInfo);
    	
    	GenericResponse responseBody = new GenericResponse(context.getMessages().getMessage("message.updateUserInfoSuccess", null, locale));
        return new ResponseEntity<GenericResponse>(responseBody, new HttpHeaders(), HttpStatus.OK);
    }
    
}