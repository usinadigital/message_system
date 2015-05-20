package br.usinadigital.msgsystemwebapp.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.usinadigital.msgsystemwebapp.exception.CustomGenericException;

@Controller
public class ProvaController {

	private static final Logger logger = LoggerFactory.getLogger(ProvaController.class);

	@RequestMapping(value = "/provacss", method = RequestMethod.GET)
	public String provacss() {
		return "provacss";
	}

	@RequestMapping(value = "/exception1", method = RequestMethod.GET)
	public ModelAndView getPages1() {
 
		logger.info("sto per lanciare la eccezione");
		throw new CustomGenericException("E888", "This is custom message X");
	}
	
	@RequestMapping(value = "/exception2", method = RequestMethod.GET)
	public ModelAndView getPages2() throws Exception {
		
		throw new IOException("CIAO sono una eccezione IO");
	}
}
