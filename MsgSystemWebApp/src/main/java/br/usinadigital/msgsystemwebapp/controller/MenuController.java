package br.usinadigital.msgsystemwebapp.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

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
