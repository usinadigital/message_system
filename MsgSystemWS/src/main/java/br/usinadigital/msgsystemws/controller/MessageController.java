package br.usinadigital.msgsystemws.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.usinadigital.msgsystemws.dao.MessageDAO;
import br.usinadigital.msgsystemws.model.Category;
import br.usinadigital.msgsystemws.model.Message;
import br.usinadigital.msgsystemws.service.MessageGCM;
import br.usinadigital.msgsystemws.util.Constants;
 
@Controller
public class MessageController {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	@Autowired
	private MessageDAO messageDAO;
	
	@Autowired
	MessageGCM messageGCM;
	
	@RequestMapping(value = Constants.GET_TEST_MESSAGE, method = RequestMethod.GET)
	public @ResponseBody Message getTestMessage() {
		
		logger.info("Begin request test Message");
		Message cat = new Message();
		cat.setId(0);
		cat.setText("test_text");		
		logger.info("End request test Message");
		
		return cat;
	}
	
	@RequestMapping(value = Constants.GET_ALL_MESSAGE, method = RequestMethod.GET)
	public @ResponseBody List<Message> getAllMessages() {
		
		logger.info("Requesting all messages");
		List<Message> list = messageDAO.getAll();

		for (Message msg : list) {
			logger.info("**************************** Message List::" + msg);
			if ( msg.getCategories().size() != 0){
				logger.info("************* Message with categories");
			}
		}
		logger.info("Request closed.");
		
		return list;
	}

	@RequestMapping(value = Constants.SAVE_MESSAGE, method = RequestMethod.POST)
	public @ResponseBody Message save(@RequestBody String msg) {
		
		logger.info("Start request: " + Constants.SAVE_MESSAGE);
		Message msgx = new Message();
		msgx.setText("Ciao testo!");
		Category category1 = new Category("xxxnomecat1");
		Category category2 = new Category("xxxnomecat2");
		Set<Category> categories = new HashSet<Category>();
		categories.add(category1);
		categories.add(category2);
		msgx.setCategories(categories);
				
		messageDAO.save(msgx);
		logger.info("Stop request: " + Constants.SAVE_MESSAGE);
		
		return msgx;
	}
	
	@RequestMapping(value = Constants.SEND_MESSAGE_BY_CATEGORIES, method = RequestMethod.POST)
	public @ResponseBody Message sendMessageByCategories(@RequestBody Message message) {
		
		logger.info("Start request: " + Constants.SEND_MESSAGE_BY_CATEGORIES);
		logger.info("Message to send: " + message);
		messageDAO.save(message);
		messageGCM.send(message);
		logger.info("Stop request: " + Constants.SEND_MESSAGE_BY_CATEGORIES);
		
		return null;
	}
	
}
