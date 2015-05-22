package br.usinadigital.msgsystemwebapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	Map<String, Integer> categoriesMap = new HashMap<String, Integer>();;
	String[] caregoriesArray;
	
	@RequestMapping(method = RequestMethod.GET)
	public String messageForm(Model model) {
		logger.info("Start GET Request: " + Constants.GET_MESSAGE);
		WSManager wsManager = (WSManager) appContext.getBean(WSManager.class);
		categories = wsManager.getAllCategories();
		initValues(model);
		printTodo(model);
		logger.info("Stop GET Request: " + Constants.GET_MESSAGE);
		return Constants.GET_MESSAGE;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String sendMessage(@ModelAttribute("message") Message message, Model model) {
		
		logger.info("Start POST Request: " + Constants.GET_MESSAGE);
		if ( hasErrors(model,message) ) {
			logger.info("Fields validated with errors");
			printMessage("Message=",message);
			printTodo(model);
			//initValues(model);
			logger.info("Stop POST Request: " + Constants.GET_MESSAGE);
			return Constants.GET_MESSAGE;
		} else {
			logger.info("Fields validated with success");
//			moveNameToId(message);
			WSManager wsManager = (WSManager) appContext.getBean(WSManager.class);
			printMessage("Message=",message);
			printTodo(model);
//			wsManager.sendMessageByCategories(message);
			logger.info("Stop POST Request: " + Constants.GET_MESSAGE);
			return "messageResult";
		}
	}

	private void printTodo(Model model){
		printMessage("Model.Message=",(Message)model.asMap().get("message"));
		logger.debug("Model.cats="+model.asMap().get("cats"));
		logger.debug("Model.categories="+model.asMap().get("categories"));
	}
	
	private String printCats(Map<String, Integer> cats){
		String c="";
		if (cats != null){
			for(String item : cats.keySet()){
				c = c + item + " | ";
			}
		}else{
			c="NULL";
		}
		return c;
	}
	
	private void printMessage(String str,Message message){
		logger.debug(str);
		logger.debug("Message.Text="+message.getText());
		if ( message.getCategories() != null && message.getCategories().size() != 0){
			for(Category item : message.getCategories()){
				logger.debug("Message.Categories.Category["+item+"]");
			}
		} else {
			if ( message.getCategories() == null){
				logger.debug("Message.Categories.Size = NULL");
			}else{
				logger.debug("Message.Categories = 0");
			}
		}
		logger.debug("Message.cats=",printCats(message.getCats()));
	}
	
	private void initValues(Model model){
		Message message = new Message();
		
		for (Category item : categories){
			categoriesMap.put(item.getName(), item.getId());
		}
		
		TODO devo usare o solo la mappa o la mappa e una lista 
		model.addAttribute("cats", categoriesMap.keySet());
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
