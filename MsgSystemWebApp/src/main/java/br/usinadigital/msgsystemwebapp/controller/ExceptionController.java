package br.usinadigital.msgsystemwebapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import br.usinadigital.msgsystemwebapp.exception.CustomGenericException;
import br.usinadigital.msgsystemwebapp.util.Constants;

@ControllerAdvice
public class ExceptionController {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView handleAllException(Exception ex) {
 
		logger.error("Exception: ",ex);
		ModelAndView model = new ModelAndView(Constants.GENERIC_ERROR_PAGE);
		model.addObject("errMsg", ex.getMessage());
		return model;
	}
	
	@ExceptionHandler(CustomGenericException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView handleCustomException(CustomGenericException ex) {
 
		logger.error("Exception: ",ex);
		ModelAndView model = new ModelAndView(Constants.GENERIC_ERROR_PAGE);
		model.addObject("errCode", ex.getCode());
		model.addObject("errMsg", ex.getMessage());
		
		return model;
	}
 
}
