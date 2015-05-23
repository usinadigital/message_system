package br.usinadigital.msgsystemwebapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import br.usinadigital.msgsystemwebapp.model.Category;
import br.usinadigital.msgsystemwebapp.model.Message;
import br.usinadigital.msgsystemwebapp.util.ApplicationConfig;

public class WSManagerImpl implements WSManager {
	
	private static final Logger logger = LoggerFactory.getLogger(WSManagerImpl.class);
	
	@Autowired
	private ApplicationConfig applicationConfig;
	
	public void save(Message message){
	}
	
	public void sendMessageByCategories(Message message) {
		String service = applicationConfig.getWsURL() + applicationConfig.getWsSendMessageByCategories();
		logger.info("Start Service " + service);
		RestTemplate restTemplate = new RestTemplate();
        Message response = restTemplate.postForObject(service, message, Message.class);
        //TODO devo controllare se é un json di errore e gestire l´errore
        logger.info("End Service: " + service);
	}

	public Category[] getAllCategories() {
		String service = applicationConfig.getWsURL() + applicationConfig.getWsGetAllCategories();
		logger.info("Start Service " + service);
		RestTemplate restTemplate = new RestTemplate();
        Category[] categories = restTemplate.getForObject(service, Category[].class);
        logger.info("End Service: " + service);
        
        return categories;
	}
}
