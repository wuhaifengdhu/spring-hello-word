package sg.ntu.itcm.service.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import sg.ntu.itcm.service.LocalizationMessagesService;

@Service("localizationMessagesService")
public class LocalizationMessagesServiceImpl implements LocalizationMessagesService {

	@Override
	public ApplicationContext getMessages() {
		return new ClassPathXmlApplicationContext("xml/mvc/spring-localization.xml");
	}
	
}
