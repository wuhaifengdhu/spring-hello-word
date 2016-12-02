package sg.ntu.itcm.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import io.jstack.sendcloud4j.mail.Result;
import sg.ntu.itcm.service.EmailService;

public class RabbitEmailMessageListener implements MessageListener {
	
	private static Logger log = LoggerFactory.getLogger(RabbitEmailMessageListener.class);

	@Autowired
	private EmailService emailService;
	
	@Override
	public void onMessage(Message message) {
		String messageBody = new String(message.getBody());
		log.info("Received email message: {}", messageBody);
		
		Result result = emailService.sendEmail(messageBody);
		
		if (result.isSuccess()) {
			log.info(String.format("Successfully send email: %s. %s", result.getMessage(), messageBody));
		} else {
			log.error(String.format("Fail to send email: %s. %s", result.getMessage(), messageBody));
		}
	}

}
