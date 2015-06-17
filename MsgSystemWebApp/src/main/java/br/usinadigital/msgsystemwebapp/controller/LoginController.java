package br.usinadigital.msgsystemwebapp.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.usinadigital.msgsystemwebapp.util.Constants;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	ApplicationContext appContext;
	
	@RequestMapping(value = "/" + Constants.GET_LOGIN, method = RequestMethod.GET)
	public ModelAndView login(HttpSession session,	@RequestParam(value = "error", required = false) String error, 
								@RequestParam(value = "logout", required = false) String logout) throws Throwable {

		logger.info("Start Request: " + Constants.GET_LOGIN);
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("messageERROR1", appContext.getMessage("view.message.error.loginNotValid", null, Locale.getDefault()));
			Throwable exception = (Throwable) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
			if (exception != null && exception instanceof AuthenticationServiceException){
				logger.error("Exception: " + exception.toString());
				model.addObject("messageERROR1", appContext.getMessage("view.message.error.dberror", null, Locale.getDefault()));
			}
		}
		if (logout != null) {
			logger.info("Logged out.");
			model.addObject("messageOK", appContext.getMessage("view.message.info.logout", null, Locale.getDefault()));
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
