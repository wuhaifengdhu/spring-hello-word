package sg.ntu.itcm.initialization;

import java.io.IOException;
import java.util.UUID;

import sg.ntu.itcm.dao.UserRolesMapper;
import sg.ntu.itcm.constants.UserLoginType;
import sg.ntu.itcm.constants.UserRole;
import sg.ntu.itcm.dao.UserMapper;
import sg.ntu.itcm.model.UserRoles;
import sg.ntu.itcm.service.GenericService;
import sg.ntu.itcm.util.BCrypt;
import sg.ntu.itcm.util.DateFormatUtil;
import sg.ntu.itcm.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.jstack.sendcloud4j.SendCloud;

@Component
public class ItcmSystemInitislization implements ApplicationListener<ContextRefreshedEvent> {
	private static Logger log = LoggerFactory.getLogger(ItcmSystemInitislization.class);
	
	// admin user information
	private static String adminEmail = "huwe0012@e.ntu.edu.sg";
	private static String adminPassword = "sgntuitcm2016";
	private static String salt = "$2a$11$o8vgy4olY7wcraHQKm4sqO";

    private boolean alreadySetup = false;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRolesMapper userRolesMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private GenericService genericService;

    // API
    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        
        User admin = userMapper.selectByEmail(adminEmail);
        
        if (admin == null) {
        	createAdminUser();
			log.info("Admin user is created successfully!");
        }
        else {
        	log.info("Admin user had been created!");
        }

        alreadySetup = true;
    }
    
    private void createAdminUser() {
    	//log.info("salt: {" + BCrypt.gensalt(11) + "}"); //create a valid salt
    	String hashedPassword = BCrypt.hashpw(adminPassword, salt);
    	
    	final User user = new User(UserLoginType.ITCM_USER);
        user.setUserUuid(UUID.randomUUID().toString());
        user.setFirstName("Mr");
        user.setLastName("ITCM");
        user.setPassword(passwordEncoder.encode(hashedPassword));
        user.setEmail(adminEmail);
        user.setAge(50);
        user.setGender("Male");
        user.setActivationStatus("YES");
        
        DateFormatUtil dateFormatUtil = new DateFormatUtil();
        user.setCreateTime(dateFormatUtil.getCurrentDate());
        user.setModificationTime(dateFormatUtil.getCurrentDate());
        userMapper.insert(user);
        
        userRolesMapper.insert(new UserRoles(user.getUserUuid(), UserRole.ROLE_ADMIN));
        userRolesMapper.insert(new UserRoles(user.getUserUuid(), UserRole.ROLE_USER));
        
        try {
			genericService.createUserDefaultAvatar(user.getUserUuid());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    // initialize the sendCloud Email service
    @Value("${sendcloud.apiUser}")
    private String apiUser;
    
    @Value("${sendcloud.apiKey}")
    private String apiKey;
    
    @Bean
	public SendCloud sendCloud() {
		log.info("SendCloud init.");
		
		SendCloud sendCloud = SendCloud.createWebApi(apiUser, apiKey);
		sendCloud.connectTimeout(30000);
		sendCloud.socketTimeout(30000);
		
		return sendCloud;
	}
}