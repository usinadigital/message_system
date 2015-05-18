package br.usinadigital.msgsystemwebapp.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import br.usinadigital.msgsystemwebapp.model.Category;
import br.usinadigital.msgsystemwebapp.util.Constants;

@Controller
public class MenuController {
	
	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
	
	@RequestMapping(value = { "/", "/" + Constants.GET_HOME }, method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
		return Constants.GET_HOME;
    }
	
	@RequestMapping(value = "/" + Constants.GET_MESSAGE, method = RequestMethod.GET)
    public String message() {
		
		RestTemplate restTemplate = new RestTemplate();
		logger.info("Send request...");
        Category categories = restTemplate.getForObject("http://localhost:8080/MsgSystemWS-0.0.1/rest/category/test", Category.class);
        logger.info("categories="+categories.toString());
        
        return Constants.GET_MESSAGE;
    }
	
	@RequestMapping(value = "/" + Constants.GET_ADMINISTRATION, method = RequestMethod.GET)
    public String administration() {
        return Constants.GET_ADMINISTRATION;
    }
	
	@RequestMapping(value = "/" + Constants.GET_LOGOUT, method = RequestMethod.GET)
    public String logout() {
        return Constants.GET_LOGOUT;
    }
	
}
