package br.usinadigital.msgsystemws.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

import br.usinadigital.msgsystemws.dao.MessageDAO;
import br.usinadigital.msgsystemws.model.Category;
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
		
		logger.info("Requesting all messages");
		
		String urlValueAppContext = servletContext.getInitParameter("contextConfigLocation");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(urlValueAppContext);
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

	@RequestMapping(value = Constants.CREATE_MESSAGE, method = RequestMethod.POST)
	public @ResponseBody Message save(@RequestBody String msg) {
		
		logger.info("Requesting save message");
		String urlValueAppContext = servletContext.getInitParameter("contextConfigLocation");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(urlValueAppContext);
		MessageDAO messageDAO = context.getBean(MessageDAO.class);
		
		Message msgx = new Message();
		msgx.setText("Ciao testo!");
		Category category1 = new Category("xxxnomecat1");
		Category category2 = new Category("xxxnomecat2");
		Set<Category> categories = new HashSet<Category>();
		categories.add(category1);
		categories.add(category2);
		msgx.setCategories(categories);
				
		messageDAO.save(msgx);
		context.close();
		logger.info("Request closed.");
		
		return msgx;
	}
	
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
