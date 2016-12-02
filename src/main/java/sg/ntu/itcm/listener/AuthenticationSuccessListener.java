package sg.ntu.itcm.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import sg.ntu.itcm.service.LoginAttemptService;

@Component
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {
	private static Logger log=LoggerFactory.getLogger(AuthenticationSuccessListener.class);

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Override
    public void onApplicationEvent(final AuthenticationSuccessEvent e) {
    	if (e.getAuthentication().getDetails() instanceof WebAuthenticationDetails) {
    		final WebAuthenticationDetails auth = (WebAuthenticationDetails) e.getAuthentication().getDetails();
            if (auth != null) {
                loginAttemptService.loginSucceeded(auth.getRemoteAddress());
            }
    	}
    	else {
    		log.info("other authertication success type");
    	}
    }

}
