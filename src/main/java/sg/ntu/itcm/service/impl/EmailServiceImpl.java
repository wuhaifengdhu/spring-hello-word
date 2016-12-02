package sg.ntu.itcm.service.impl;


import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import io.jstack.sendcloud4j.SendCloud;
import io.jstack.sendcloud4j.mail.Email;
import io.jstack.sendcloud4j.mail.Result;
import io.jstack.sendcloud4j.mail.Substitution;
import io.jstack.sendcloud4j.mail.Substitution.Sub;
import sg.ntu.itcm.email.EmailModel;
import sg.ntu.itcm.email.EmailTemplates;
import sg.ntu.itcm.email.SendCloudEmailTemplate;
import sg.ntu.itcm.event.OnRegistrationCompleteEvent;
import sg.ntu.itcm.model.PasswordResetToken;
import sg.ntu.itcm.model.User;
import sg.ntu.itcm.service.EmailService;
import sg.ntu.itcm.service.GenericService;
import sg.ntu.itcm.service.LocalizationMessagesService;

@Service("emailService")
public class EmailServiceImpl implements EmailService {
	
	private static Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);
	
	@Resource
	private AmqpTemplate amqpTemplate;
	
	@Autowired
    private LocalizationMessagesService context;
	
	@Autowired
    private GenericService genericService;
	
	@Autowired
	private SendCloud sendCloud;
	
	// ==== send email request to rabbitMQ server for CloudSend Email service ====
	@Override
	public void sendUserActivationEmail(User user, OnRegistrationCompleteEvent event, String token) {
		EmailModel emailBody = createGenericEmailTemplate(user);
		emailBody.setSubject(context.getMessages().getMessage("message.registration.subject", null, event.getLocale()));
		emailBody.setFromName(context.getMessages().getMessage("message.from", null, event.getLocale()));
		emailBody.setUrlLink(event.getAppUrl() + "/regitrationConfirm.html?token=" + token);
        
        sendEmailToRabbitQueue(emailBody, new EmailTemplates().getUserActivationEmailTemplate(emailBody.getUsername(), emailBody.getUrlLink()));
	}
	
	@Override
	public void resendUserActivationEmail(final User user, final Locale locale, final HttpServletRequest request, final String newToken) {
		EmailModel emailBody = createGenericEmailTemplate(user);
		emailBody.setSubject(context.getMessages().getMessage("message.registration.subject", null, locale));
		emailBody.setFromName(context.getMessages().getMessage("message.from", null, locale));
		emailBody.setUrlLink(genericService.getAppUrl(request) + "/regitrationConfirm.html?token=" + newToken);
        
        sendEmailToRabbitQueue(emailBody, new EmailTemplates().getUserActivationEmailTemplate(emailBody.getUsername(), emailBody.getUrlLink()));
	}
	
	@Override
	public void sendResetPasswordEmail(final User user, final Locale locale, final HttpServletRequest request, final PasswordResetToken passwordResetToken) {
		EmailModel emailBody = createGenericEmailTemplate(user);
		emailBody.setSubject(context.getMessages().getMessage("message.resetPassword.subject", null, locale));
		emailBody.setFromName(context.getMessages().getMessage("message.from", null, locale));
		emailBody.setUrlLink(genericService.getAppUrl(request) + "/user/changePassword?id=" + passwordResetToken.getId() + "&token=" + passwordResetToken.getUserPasswordResetToken());
        
        sendEmailToRabbitQueue(emailBody, new EmailTemplates().getUserActivationEmailTemplate(emailBody.getUsername(), emailBody.getUrlLink()));
	}
	
	private EmailModel createGenericEmailTemplate(final User user) {
		EmailModel emailBody = new EmailModel();
		emailBody.setToEmail(user.getEmail());
		emailBody.setUsername(user.getFirstName() + " " + user.getLastName());
		return emailBody;
	}

	private void sendEmailToRabbitQueue(EmailModel emailBody, SendCloudEmailTemplate emailTemplate) {
		JSONObject sendEmail = new JSONObject();
		sendEmail.put("subject", emailBody.getSubject());
		sendEmail.put("fromEmail", emailBody.getFromEmail());
		sendEmail.put("fromName", emailBody.getFromName());
		sendEmail.put("toEmail", emailBody.getToEmail());
		
		sendEmail.put("templateName", emailTemplate.getTemplateName());
		sendEmail.put("templateMap", emailTemplate.getTemplateMap());
		
		String message = sendEmail.toJSONString();
		log.info("send email message: {}", message);
		
		amqpTemplate.convertAndSend("emailExchange", "emailQueueKey", message);
	}
	
	// ==== use the sendCloud to send email ====
	@Override
	public Result sendEmail(String message) {
		JSONObject emailBody = JSONObject.parseObject(message);
		String subject = emailBody.getString("subject");
		String fromEmail = emailBody.getString("fromEmail");
		String fromName = emailBody.getString("fromName");
		String toEmail = emailBody.getString("toEmail");
		String templateName = emailBody.getString("templateName");
		Map<String, String> templateMap = emailBody.getJSONObject("templateMap").entrySet().stream()
				.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().toString()));
		SendCloudEmailTemplate template = new SendCloudEmailTemplate(templateName, templateMap);
		
		return send(subject, fromEmail, fromName, toEmail, template);
	}
	
	// private sendCloud email service
	private Result send(String subject, String fromEmail, String fromName, String toEmail, SendCloudEmailTemplate emailTemplate) {
		Sub sub = Substitution.sub();
		for (Entry<String, String> entry: emailTemplate.getTemplateMap().entrySet()) {
			sub.set(entry.getKey(), entry.getValue());
		}
		Email<?> em = Email.template(emailTemplate.getTemplateName())
				.substitutionVars(sub)
				.subject(subject)
				.from(fromEmail)
				.fromName(fromName)
				.to(toEmail);
		return sendCloud.mail().send(em);
	}

}
