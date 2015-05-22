package br.usinadigital.msgsystemwebapp.controller;

import java.util.HashSet;

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

	private static final Logger logger = LoggerFactory
			.getLogger(MessageController.class);

	@Autowired
	private ApplicationContext appContext;

	Category[] categories;
	
	@RequestMapping(method = RequestMethod.GET)
	public String messageForm(Model model) {
		logger.info("Start GET Request: " + Constants.GET_MESSAGE);
		WSManager wsManager = (WSManager) appContext.getBean(WSManager.class);
		categories = wsManager.getAllCategories();
		initValues(model);
		logger.info("Stop GET Request: " + Constants.GET_MESSAGE);

		return Constants.GET_MESSAGE;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String sendMessage(@ModelAttribute("message") Message message, Model model) {
		
		logger.info("Start POST Request: " + Constants.GET_MESSAGE);
		if ( hasErrors(model,message) ) {
			logger.info("Fields validated with errors");
			logger.debug("Message="+message);
			logger.info("Stop POST Request: " + Constants.GET_MESSAGE);
			//initValues(model);
			return Constants.GET_MESSAGE;
		} else {
			logger.info("Fields validated with success");
			moveNameToId(message);
			WSManager wsManager = (WSManager) appContext.getBean(WSManager.class);
			logger.debug("Message="+message);
//			wsManager.sendMessageByCategories(message);
			logger.info("Stop POST Request: " + Constants.GET_MESSAGE);
			return "messageResult";
		}
	}

	private void initValues(Model model){
		Message message = new Message();
		message.setCategories(new HashSet<Category>(0));
		model.addAttribute("categories", categories);
		model.addAttribute("message", message);
		model.addAttribute("textError","");
		model.addAttribute("categoriesError","");
	}
	
	private boolean hasErrors(Model model, Message message){
		if (message.getText().length() == 0){
			model.addAttribute("textError","eeee");
			return true;
		}
		if (message.getCategories() == null){
			model.addAttribute("categoriesError","xxxx");
			return true;
		}
		
		return false;
	}
	/*
	 * The message returned from the form put the category "Id" in the field
	 * "name"
	 */
	private void moveNameToId(Message message) {
		if (message.getCategories() != null){
			for (Category item : message.getCategories()) {
					item.setId(Integer.parseInt(item.getName()));
					item.setName("");
			}
		}
	}
}
