package br.usinadigital.msgsystemwebapp.controller;

import br.usinadigital.msgsystemwebapp.util.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/" + Constants.GET_LOGIN, method = RequestMethod.GET)
	public ModelAndView login(	@RequestParam(value = "error", required = false) String error, 
								@RequestParam(value = "logout", required = false) String logout) {

		logger.info("Start Request: " + Constants.GET_LOGIN);
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}
		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName(Constants.GET_LOGIN);
		logger.info("Stop Request: " + Constants.GET_LOGIN);
		
		return model;
	}

	// for 403 access denied page
	@RequestMapping(value = "/" + Constants.GET_403PAGE, method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());
		}

		model.setViewName(Constants.GET_403PAGE);
		return model;
	}
}
