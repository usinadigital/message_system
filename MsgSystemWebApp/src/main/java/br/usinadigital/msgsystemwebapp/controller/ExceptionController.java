package br.usinadigital.msgsystemwebapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import br.usinadigital.msgsystemwebapp.exception.CustomGenericException;
import br.usinadigital.msgsystemwebapp.util.Constants;

@ControllerAdvice
public class ExceptionController {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	@ExceptionHandler(CustomGenericException.class)
	public ModelAndView handleCustomException(CustomGenericException ex) {
 
		logger.info("ECCEZIONE");
		logger.error("Exception: ",ex);
		ModelAndView model = new ModelAndView(Constants.GENERIC_ERROR_PAGE);
		model.addObject("errCode", ex.getCode());
		model.addObject("errMsg", ex.getMessage());
		
		return model;
	}
 
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
 
		logger.error("Exception: ",ex);
		ModelAndView model = new ModelAndView(Constants.GENERIC_ERROR_PAGE);
		model.addObject("errMsg", ex.getMessage());
		return model;
	}
}
