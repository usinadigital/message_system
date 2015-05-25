package br.usinadigital.msgsystemws.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.usinadigital.msgsystemws.dao.MessageDAO;
import br.usinadigital.msgsystemws.dao.MessageDAOImpl;
import br.usinadigital.msgsystemws.model.Message;
import br.usinadigital.msgsystemws.util.Constants;

public class MessageGCMImpl implements MessageGCM{

	private static final Logger logger = LoggerFactory.getLogger(MessageGCMImpl.class);

	
	public void send(Message message){
		logger.info("Start service GCM: Send Message");
		logger.info("Message to send: " + message);

		logger.info("Stop service GCM: Send Message");
	}
}
