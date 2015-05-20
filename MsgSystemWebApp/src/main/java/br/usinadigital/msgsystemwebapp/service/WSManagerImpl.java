package br.usinadigital.msgsystemwebapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import br.usinadigital.msgsystemwebapp.model.Category;
import br.usinadigital.msgsystemwebapp.model.Message;
import br.usinadigital.msgsystemwebapp.util.ApplicationConfig;
import br.usinadigital.msgsystemwebapp.util.Constants;

public class WSManagerImpl implements WSManager {
	
	private static final Logger logger = LoggerFactory.getLogger(WSManagerImpl.class);
	
	private ApplicationConfig applicationConfig;
	
	public void setApplicationConfig(ApplicationConfig applicationConfig) {
		this.applicationConfig = applicationConfig;
	}
	
	public void sendMessageToUsersByCategories(Message message) {
		String service = applicationConfig.getSendMessageToUsersByCategories();
		RestTemplate restTemplate = new RestTemplate();
        Message response = restTemplate.postForObject(service, message, Message.class);
        //TODO devo controllare se é un json di errore e gestire l´errore
	}

	public Category[] getAllCategories() {
		String service = applicationConfig.getGetAllCategories();
		RestTemplate restTemplate = new RestTemplate();
		logger.info("Start Service " + service);
        Category[] categories = restTemplate.getForObject(service, Category[].class);
        logger.info("End Service: " + service);
        
        return categories;
	}
}
