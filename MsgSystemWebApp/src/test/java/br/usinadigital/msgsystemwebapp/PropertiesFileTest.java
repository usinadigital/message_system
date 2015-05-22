package br.usinadigital.msgsystemwebapp;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.usinadigital.msgsystemwebapp.util.ApplicationConfig;

public class PropertiesFileTest {
	private static final Logger logger = LoggerFactory.getLogger(PropertiesFileTest.class);

	@Autowired
	static ApplicationConfig config;
	
	public static void main(String[] args) throws ParseException {

		logger.info("Prova Test");
		
		logger.info("config="+config.getWsURL());
	}
}
