package sg.ntu.itcm.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import sg.ntu.itcm.service.LoginAttemptService;

@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
	private static Logger log=LoggerFactory.getLogger(AuthenticationFailureListener.class);

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Override
    public void onApplicationEvent(final AuthenticationFailureBadCredentialsEvent e) {
        if (e.getAuthentication().getDetails() instanceof WebAuthenticationDetails) {
    		final WebAuthenticationDetails auth = (WebAuthenticationDetails) e.getAuthentication().getDetails();
            if (auth != null) {
                loginAttemptService.loginFailed(auth.getRemoteAddress());
            }
    	}
    	else {
    		log.info("other authertication falure type");
    	}
    }

}