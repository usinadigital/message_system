package br.usinadigital.msgsystemwebapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.usinadigital.msgsystemwebapp.model.Category;
import br.usinadigital.msgsystemwebapp.model.Message;
import br.usinadigital.msgsystemwebapp.service.WSManager;
import br.usinadigital.msgsystemwebapp.util.Constants;

@Controller
@RequestMapping(value = "/" + Constants.GET_MESSAGE)
public class MessageController {
		
		private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
		
		@Autowired
		private ApplicationContext appContext;
			
		@RequestMapping(method = RequestMethod.GET)
	    public String messageForm(Model model) {
			logger.info("Start Request GET: " + Constants.GET_MESSAGE);
			
			WSManager wsManager = (WSManager)appContext.getBean(WSManager.class);
			Category[] categories = wsManager.getAllCategories();
			Message message = new Message();
	        model.addAttribute("categories",categories);
	        model.addAttribute("message",message);
	        
	        logger.info("End Request GET: " + Constants.GET_MESSAGE);
	        
	        return Constants.GET_MESSAGE;
	    }
		
		@RequestMapping(method = RequestMethod.POST)
	    public String sendMessage(@ModelAttribute("message")Message message, Model model) {
			
			logger.info("Start Request POST: " + Constants.GET_MESSAGE);
			
			//TODO Fare la validazione
			moveNameToId(message);
			WSManager wsManager = (WSManager)appContext.getBean(WSManager.class);
			wsManager.sendMessageToUsersByCategories(message);
			
			logger.info("End Request POST: " + Constants.GET_MESSAGE);
	        
	        return "messageResult";
	    }
		
		/*
			The message returned from the form put the category "Id" in the field "name"
		*/
		private void moveNameToId(Message message){
			for (Category item : message.getCategories()) {
				item.setId(Integer.parseInt(item.getName()));
				item.setName("");
			}
		}
	}
