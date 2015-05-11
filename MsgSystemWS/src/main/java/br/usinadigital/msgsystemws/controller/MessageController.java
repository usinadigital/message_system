package br.usinadigital.msgsystemws.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

import br.usinadigital.msgsystemws.dao.MessageDAO;
import br.usinadigital.msgsystemws.model.Message;
import br.usinadigital.msgsystemws.util.Constants;
 
@Controller
public class MessageController implements ServletContextAware {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	private ServletContext servletContext;
	
	@RequestMapping(value = Constants.GET_TEST_MESSAGE, method = RequestMethod.GET)
	public @ResponseBody Message getTestCategorie() {
		
		logger.info("Begin request test Message");
		
		Message cat = new Message();
		cat.setId(0);
		cat.setText("test_text");
				
		logger.info("End request test Message");
		
		return cat;
	}
	
	@RequestMapping(value = Constants.GET_ALL_MESSAGE, method = RequestMethod.GET)
	public @ResponseBody List<Message> getAllCategories() {
		
		logger.info("Requesting all categories");
		
		String urlValue = servletContext.getInitParameter("contextConfigLocation");		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(urlValue);
		
		MessageDAO MessageDAO = context.getBean(MessageDAO.class);
		List<Message> list = MessageDAO.list();

		for (Message msg : list) {
			logger.info("**************************** Message List::" + msg);
			if ( msg.getCategories().size() != 0){
				logger.info("************* Message with categories");
			}
		}
		
		context.close();
		logger.info("Request closed.");
		
		return list;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
