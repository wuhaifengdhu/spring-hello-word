package sg.ntu.itcm.listener;

import java.util.UUID;

import sg.ntu.itcm.model.User;
import sg.ntu.itcm.event.OnRegistrationCompleteEvent;
import sg.ntu.itcm.service.EmailService;
import sg.ntu.itcm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
	
    @Autowired
    private UserService service;

    @Autowired
	private EmailService emailService;

    // API
    @Override
    public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(final OnRegistrationCompleteEvent event) {
        final User user = event.getUser();
        final String token = UUID.randomUUID().toString();
        service.createVerificationTokenForUser(user, token);
        
        emailService.sendUserActivationEmail(user, event, token);
    }
}
