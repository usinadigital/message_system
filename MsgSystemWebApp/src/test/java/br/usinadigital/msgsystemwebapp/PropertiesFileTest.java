package br.usinadigital.msgsystemwebapp;

import java.text.ParseException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.usinadigital.msgsystemwebapp.util.ApplicationConfig;

public class PropertiesFileTest {
	private static final Logger logger = LoggerFactory.getLogger(PropertiesFileTest.class);

	@Autowired
	static ApplicationContext appContext;
	
	public static void main(String[] args) throws ParseException {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/dispatcher-context.xml");
//		ApplicationConfig config = context.getBean(ApplicationConfig.class);
		
		logger.info("Prova Test");
		
		logger.info("config="+context.getMessage("view.login.username", null, Locale.getDefault()));
	}
}
