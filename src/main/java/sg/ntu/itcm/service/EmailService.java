package sg.ntu.itcm.service;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import io.jstack.sendcloud4j.mail.Result;
import sg.ntu.itcm.event.OnRegistrationCompleteEvent;
import sg.ntu.itcm.model.PasswordResetToken;
import sg.ntu.itcm.model.User;

public interface EmailService {
	
	void sendUserActivationEmail(final User user, final OnRegistrationCompleteEvent event, final String token);

	void resendUserActivationEmail(final User user, final Locale locale, final HttpServletRequest request, final String newToken);
	
	void sendResetPasswordEmail(final User user, final Locale locale, final HttpServletRequest request, final PasswordResetToken passwordResetToken);
	
	Result sendEmail(String message);
}
