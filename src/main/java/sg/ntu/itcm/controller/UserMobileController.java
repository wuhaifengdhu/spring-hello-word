package sg.ntu.itcm.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import sg.ntu.itcm.dto.UserInfoDto;
import sg.ntu.itcm.model.User;
import sg.ntu.itcm.service.GenericService;
import sg.ntu.itcm.service.LocalizationMessagesService;
import sg.ntu.itcm.service.UserService;
import sg.ntu.itcm.util.GenericResponse;

@Controller
@RequestMapping("/api/user")  
public class UserMobileController {

	private static Logger log = LoggerFactory.getLogger(UserMobileController.class);
	
	@Autowired  
    private UserService userService;
	
	@Autowired
    private GenericService genericService;
	
	@Autowired
    private LocalizationMessagesService context;
	
	@GetMapping(value="/userInfo")
    public ResponseEntity<Object> getUserInfo(){
    	final String email = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("user email: "+ email);
        return new ResponseEntity<Object>(userService.getUserInfo(email), new HttpHeaders(), HttpStatus.OK);
    }
    
    @PostMapping(value = "/updatePassword")
    public ResponseEntity<Object> changeUserPasswordMobile(final Locale locale, @RequestParam("password") final String password, @RequestParam("oldpassword") final String oldPassword) {
    	
    	final User user = userService.changeUserPassword(locale, password, oldPassword);
    	
    	JSONObject data  = new JSONObject();
		data.put("message", context.getMessages().getMessage("message.updatePasswordSuc", null, locale));
		data.put("user", user.getEmail());
    	return new ResponseEntity<Object>(data.toJSONString(), new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping(value = "/userAvatar")
    public ResponseEntity<byte[]> getUserAvatar() throws FileNotFoundException, IOException {
    	return userService.downloadUserAvatar();
    }
    
    @PostMapping(value = "/userAvatarUpload")
    public ResponseEntity<GenericResponse> doUploadUserAvatar(@RequestParam("file")final MultipartFile file, final Model model, final Locale locale) throws IOException {
    	if (file.isEmpty()) {	        
	        GenericResponse responseBody = new GenericResponse(context.getMessages().getMessage("message.uploadFileEmpty", null, locale));
	        return new ResponseEntity<GenericResponse>(responseBody, new HttpHeaders(), HttpStatus.OK);
	    }
	    
	    if (ImageIO.read(file.getInputStream()) == null) {
	        GenericResponse responseBody = new GenericResponse(context.getMessages().getMessage("message.wrongFileExtension", null, locale));
	        return new ResponseEntity<GenericResponse>(responseBody, new HttpHeaders(), HttpStatus.OK);
	    }
	    
	    log.info("Mobile process image file:{}", file.getOriginalFilename());
	    
		final String email = SecurityContextHolder.getContext().getAuthentication().getName();
		final User user = userService.findUserByEmail(email);
	    FileUtils.copyInputStreamToFile(genericService.resizeImage(file), new File(genericService.getUserAvatarPath() + File.separator + user.getUserUuid()));
        
        GenericResponse responseBody = new GenericResponse(context.getMessages().getMessage("message.uploadUserAvatar", null, locale));
        return new ResponseEntity<GenericResponse>(responseBody, new HttpHeaders(), HttpStatus.OK);
    }
    
    @PostMapping(value = "/updateUserInfo")
    public ResponseEntity<GenericResponse> doUploadUserAvatar(@Valid final UserInfoDto userInfo, final Locale locale, final HttpServletRequest request) {
    	
    	userService.updateUserInfo(userInfo);
    	
    	GenericResponse responseBody = new GenericResponse(context.getMessages().getMessage("message.updateUserInfoSuccess", null, locale));
        return new ResponseEntity<GenericResponse>(responseBody, new HttpHeaders(), HttpStatus.OK);
    }
}
