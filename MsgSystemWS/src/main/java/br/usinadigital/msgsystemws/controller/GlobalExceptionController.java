package br.usinadigital.msgsystemws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.usinadigital.msgsystemws.exception.CustomGenericException;
import br.usinadigital.msgsystemws.model.ErrorMessage;
import br.usinadigital.msgsystemws.util.Constants;

@ControllerAdvice
public class GlobalExceptionController extends ResponseEntityExceptionHandler{

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionController.class);
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody ErrorMessage handleAllException(Exception ex) {
 
		logger.error("Exception: ",ex);
		
		int httpError = Constants.HTTP_INTERNAL_SERVER_ERROR;
		String errorCode = Constants.GENERIC_SERVICE_ERROR;
		String userMessage = "The Web Service is not able to response. Contact the technical support.";
		String developerMessage = "Exception: " + ex.toString();
		ErrorMessage error = new ErrorMessage(httpError,errorCode,userMessage,developerMessage);
		
		return error;
	}
	
	@ExceptionHandler(CustomGenericException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody ErrorMessage handleCustomException(CustomGenericException ex) {
 
		logger.error("Exception: ",ex);
		ErrorMessage error = new ErrorMessage(Constants.HTTP_INTERNAL_SERVER_ERROR,Constants.GENERIC_SERVICE_ERROR,"The Web Service is not able to response. Contact the technical support.",ex.getMessage());
		
		return error;
	}
}
