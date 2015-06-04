package br.usinadigital.msgsystemwebapp.controller;

import java.util.HashMap;
import java.util.Locale;
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

	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

	@Autowired
	ApplicationContext appContext;
	
	@Autowired
	WSManager wsManager;
	
	Message message;
	Category[] categories;
	Map<Integer, String> categoriesMap = new HashMap<Integer, String>();;

	@RequestMapping(method = RequestMethod.GET)
	public String messageForm(Model model) {
		logger.info("Start GET Request: " + Constants.GET_MESSAGE);
		categories = wsManager.getAllCategories();
		initValues(model);
		logger.info("Stop GET Request: " + Constants.GET_MESSAGE);
		return Constants.GET_MESSAGE;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String sendMessage(@ModelAttribute("message") Message message, Model model) {

		logger.info("Start POST Request: " + Constants.GET_MESSAGE);
		if (hasErrors(model, message)) {
			logger.info("Fields validated with errors");
			// TODO doesn t keep memorized the categories selected
			moveNameToId(message);
			model.addAttribute("categories", categories);
			logger.info("Stop POST Request: " + Constants.GET_MESSAGE);
			return Constants.GET_MESSAGE;
		} else {
			logger.info("Fields validated with success");
			moveNameToId(message);
			wsManager.save(message);
			
			logger.info("Stop POST Request: " + Constants.GET_MESSAGE);
			return "messageResult";
		}
	}

	/*
	 * private void printTodo(Model model){
	 * printMessage("Model.Message=",(Message)model.asMap().get("message"));
	 * logger.debug("Model.cats="+model.asMap().get("cats"));
	 * logger.debug("Model.categories="+model.asMap().get("categories")); }
	 * 
	 * private void printMessage(String str,Message message){ logger.debug(str);
	 * logger.debug("Message.Text="+message.getText()); if (
	 * message.getCategories() != null && message.getCategories().size() != 0){
	 * for(Category item : message.getCategories()){
	 * logger.debug("Message.Categories.Category["+item+"]"); } } else { if (
	 * message.getCategories() == null){
	 * logger.debug("Message.Categories.Size = NULL"); }else{
	 * logger.debug("Message.Categories = 0"); } } }
	 */

	private void initValues(Model model) {
		for (Category item : categories) {
			categoriesMap.put(item.getId(), item.getName());
		}
		message = new Message();
		message.getCategories().add(new Category(10, "catname1"));
		model.addAttribute("categories", categories);
		model.addAttribute("message", message);
		model.addAttribute("textError", "");
		model.addAttribute("categoriesError", "");
	}

	private boolean hasErrors(Model model, Message message) {
		boolean error = false;

		if (message.getText().length() == 0) {
			model.addAttribute("messageERROR", appContext.getMessage("view.message.error.textNotEmpty", null, Locale.getDefault()));
			error = true;
		}
		if (message.getCategories() == null) {
			model.addAttribute("messageERROR2", appContext.getMessage("view.message.error.atLeastOneCategory", null, Locale.getDefault()));
			error = true;
		}

		return error;
	}

	/*
	 * The message returned from the form put the category "Id" in the field
	 * "name", because the field "id" is an integer
	 */
	private void moveNameToId(Message message) {
		if (message.getCategories() != null) {
			for (Category item : message.getCategories()) {
				item.setId(Integer.parseInt(item.getName()));
				item.setName(categoriesMap.get(item.getId()));
			}
		}
	}
}
