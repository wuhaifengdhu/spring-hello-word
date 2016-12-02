package sg.ntu.itcm.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

import javax.transaction.Transactional;

import sg.ntu.itcm.dao.PasswordResetTokenMapper;
import sg.ntu.itcm.dao.UserRolesMapper;
import sg.ntu.itcm.dao.UserMapper;
import sg.ntu.itcm.constants.UserRole;
import sg.ntu.itcm.dao.EmailVerificationTokenMapper;

import sg.ntu.itcm.model.PasswordResetToken;
import sg.ntu.itcm.model.User;
import sg.ntu.itcm.model.UserRoles;
import sg.ntu.itcm.service.GenericService;
import sg.ntu.itcm.service.UserService;
import sg.ntu.itcm.util.DateFormatUtil;
import sg.ntu.itcm.model.EmailVerificationToken;

import sg.ntu.itcm.dto.UserDto;
import sg.ntu.itcm.dto.UserInfoDto;
import sg.ntu.itcm.error.InvalidOldPasswordException;
import sg.ntu.itcm.error.UserAlreadyExistException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EmailVerificationTokenMapper emailVerificationTokenMapper;

    @Autowired
    private PasswordResetTokenMapper passwordTokenMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private GenericService genericService;

    @Autowired
    private UserRolesMapper userRolesMapper;

    public static final String TOKEN_INVALID = "invalidToken";
    public static final String TOKEN_EXPIRED = "expired";

    // API
    @Override
    public User registerNewUserAccount(final UserDto accountDto) {
        if (emailExist(accountDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email adress: " + accountDto.getEmail());
        }
        
        final User user = new User();
        user.setUserUuid(UUID.randomUUID().toString());
        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail().toLowerCase());
        user.setAge(accountDto.getAge());
        user.setGender(accountDto.getGender());
        user.setSignInProvider(accountDto.getSignInProvider());
        
        DateFormatUtil dateFormatUtil = new DateFormatUtil();
        user.setCreateTime(dateFormatUtil.getCurrentDate());
        user.setModificationTime(dateFormatUtil.getCurrentDate());
        userMapper.insert(user);
        
        userRolesMapper.insert(new UserRoles(user.getUserUuid(), UserRole.ROLE_USER));
        
        try {
			genericService.createUserDefaultAvatar(user.getUserUuid());
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return userMapper.selectByUuid(user.getUserUuid());
    }

    @Override
    public User getUser(final String verificationToken) {
    	final EmailVerificationToken emailVerificationToken = emailVerificationTokenMapper.selectByVerificationToken(verificationToken);
        final User user = userMapper.selectByUuid(emailVerificationToken.getUserUuid());
        return user;
    }

    @Override
    public EmailVerificationToken getVerificationToken(final String emailVerificationToken) {
        return emailVerificationTokenMapper.selectByVerificationToken(emailVerificationToken);
    }

    @Override
    public void saveRegisteredUser(final User user) {
    	userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void deleteUser(final User user) {
    	EmailVerificationToken verificationToken = emailVerificationTokenMapper.selectByUuid(user.getUserUuid());

        if (verificationToken != null)
        	emailVerificationTokenMapper.deleteByUuid(verificationToken.getUserUuid());

        PasswordResetToken passwordToken = passwordTokenMapper.selectByUuid(user.getUserUuid());

        if (passwordToken != null)
        	passwordTokenMapper.deleteByUuid(passwordToken.getUserUuid());

        userMapper.deleteByUuid(user.getUserUuid());
    }

    @Override
    public void createVerificationTokenForUser(final User user, final String token) {
    	final EmailVerificationToken myToken = new EmailVerificationToken(user.getUserUuid(), token);
    	
    	EmailVerificationToken emailVerificationToken = emailVerificationTokenMapper.selectByUuid(user.getUserUuid());
    	if (emailVerificationToken == null) {
    		// add record
            emailVerificationTokenMapper.insert(myToken);
    	}
    	else {
    		// update record
    		myToken.setId(emailVerificationToken.getId());
            emailVerificationTokenMapper.updateByPrimaryKeySelective(myToken);
    	}
    }

    @Override
    public EmailVerificationToken generateNewVerificationToken(final String existingVerificationToken) {
    	EmailVerificationToken vToken = emailVerificationTokenMapper.selectByVerificationToken(existingVerificationToken);
        vToken.updateToken(UUID.randomUUID().toString());
        emailVerificationTokenMapper.updateByPrimaryKeySelective(vToken);
        return emailVerificationTokenMapper.selectByUuid(vToken.getUserUuid());
    }

    @Override
    public PasswordResetToken createPasswordResetTokenForUser(final User user, final String token) {
        final PasswordResetToken myToken = new PasswordResetToken(user.getUserUuid(), token);
        
        PasswordResetToken passwordResetToken = passwordTokenMapper.selectByUuid(user.getUserUuid());
        if (passwordResetToken == null) {
        	// add record
        	passwordTokenMapper.insert(myToken);
        }
        else {
        	// update record
        	myToken.setId(passwordResetToken.getId());
        	passwordTokenMapper.updateByPrimaryKeySelective(myToken);
        }
        return passwordTokenMapper.selectByUuid(user.getUserUuid());
    }

    @Override
    public User findUserByEmail(final String email) {
        return userMapper.selectByEmail(email.toLowerCase());
    }

    @Override
    public PasswordResetToken getPasswordResetToken(final String token) {
        return passwordTokenMapper.selectByVerificationToken(token);
    }

    @Override
    public User getUserByPasswordResetToken(final String token) {
    	final PasswordResetToken passwordResetToken = passwordTokenMapper.selectByVerificationToken(token);
        return userMapper.selectByUuid(passwordResetToken.getUserUuid());
    }

    @Override
    public User getUserByID(final int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void changeUserPassword(final User user, final String password) {
        user.setPassword(passwordEncoder.encode(password));
        userMapper.updateByPrimaryKeySelective(user);
        passwordTokenMapper.deleteByUuid(user.getUserUuid());
    }

    @Override
    public boolean checkIfValidOldPassword(final User user, final String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    @Override
    public String validateVerificationToken(String token) {
        final EmailVerificationToken emailVerificationToken = emailVerificationTokenMapper.selectByVerificationToken(token);
        if (emailVerificationToken == null) {
            return TOKEN_INVALID;
        }
        
        final User user = userMapper.selectByUuid(emailVerificationToken.getUserUuid());
        final Calendar cal = Calendar.getInstance();
        if ((emailVerificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return TOKEN_EXPIRED;
        }
        
        user.setActivationStatus("YES");
        emailVerificationTokenMapper.deleteByUuid(user.getUserUuid());
        userMapper.updateByPrimaryKeySelective(user);
        return null;
    }

    private boolean emailExist(final String email) {
        final User user = userMapper.selectByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

	@Override
	public String getUserInfo(String email) {
		final User user = findUserByEmail(email);
        
        JSONObject data  = new JSONObject();
        data.put("userUuid", user.getUserUuid());
        data.put("email", user.getEmail());
        data.put("firstName", user.getFirstName());
        data.put("lastName", user.getLastName());
        data.put("age", user.getAge());
        data.put("gender", user.getGender());
        data.put("signInProvider", user.getSignInProvider());
        data.put("primaryKey", user.getId());
        
        String date = new SimpleDateFormat("yyyy-MM-dd").format(user.getCreateTime());
        data.put("createTime", date);
        
        log.info("data: " + data);
        
		return data.toJSONString();
	}
	
	@Override
	public User changeUserPassword(final Locale locale, final String password, final String oldPassword) {
    	final String email = SecurityContextHolder.getContext().getAuthentication().getName();
    	final User user = findUserByEmail(email);
        if (!checkIfValidOldPassword(user, oldPassword)) {
            throw new InvalidOldPasswordException();
        }
        changeUserPassword(user, password);
        
        return user;
    }
	
	@Override
	public ResponseEntity<byte[]> downloadUserAvatar() throws FileNotFoundException, IOException {
    	final String email = SecurityContextHolder.getContext().getAuthentication().getName();
    	final User user = findUserByEmail(email);
    	
	    String filePath = genericService.getUserAvatarPath() + File.separator + user.getUserUuid();
    	InputStream fileStream = new FileInputStream(filePath);
    	
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]>(IOUtils.toByteArray(fileStream), headers, HttpStatus.OK);
    }
	
	@Override
	public void updateUserInfo(final UserInfoDto userInfo) {
		log.info("Update user account with information: {}", userInfo);
    	
    	final User user = new User();
    	user.setAge(userInfo.getAge());
    	user.setGender(userInfo.getGender());
    	user.setFirstName(userInfo.getFirstName());
    	user.setLastName(userInfo.getLastName());
    	user.setId(userInfo.getPrimaryKey());
    	user.setActivationStatus(userInfo.getActivationStatus());
    	
    	saveRegisteredUser(user);
	}
}