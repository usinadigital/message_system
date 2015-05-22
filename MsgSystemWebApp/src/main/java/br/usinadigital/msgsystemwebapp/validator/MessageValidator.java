package br.usinadigital.msgsystemwebapp.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.usinadigital.msgsystemwebapp.model.Message;



@Component
public class MessageValidator implements Validator {
	 
	private static final Logger logger = LoggerFactory.getLogger(MessageValidator.class);

    @Override
    public boolean supports(Class<?> clazz) {
        return Message.class.isAssignableFrom(clazz);
    }
 
    @Override
    public void validate(Object target, Errors errors) {
    	logger.debug("Start validation");
    	Message message = (Message) target;
    	logger.debug("Message="+message);
//        ValidationUtils.rejectIfEmpty(errors, "text", "view.message.error.textNotEmpty","Errore!!!");
//        ValidationUtils.rejectIfEmpty(errors, "categories", "shop.emplNumber.empty");
        
    	if (message.getText() == null || message.getText().length() == 0)
            errors.rejectValue("text", "view.message.error.textNotEmpty","PIPPO");
        logger.debug("End validation");
    }
 
}
