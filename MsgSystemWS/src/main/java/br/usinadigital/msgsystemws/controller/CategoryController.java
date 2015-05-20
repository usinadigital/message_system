package br.usinadigital.msgsystemws.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

import br.usinadigital.msgsystemws.dao.CategoryDAO;
import br.usinadigital.msgsystemws.model.Category;
import br.usinadigital.msgsystemws.util.Constants;
 
@Controller
public class CategoryController {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private ApplicationContext appContext;
		
	@RequestMapping(value = Constants.GET_TEST_CATEGORY, method = RequestMethod.GET)
	public @ResponseBody Category getTestCategorie() {
		
		logger.info("Start request: " + Constants.GET_TEST_CATEGORY);
		
		Category cat = new Category();
		cat.setId(0);
		cat.setName("test_name");
		cat.setDescription("test_desc");
		cat.setValid(0);
		cat.setLastupdate(new Date());
		
		logger.info("End request: " + Constants.GET_TEST_CATEGORY);
		
		return cat;
	}
	
	@RequestMapping(value = Constants.GET_ALL_CATEGORY, method = RequestMethod.GET)
	public @ResponseBody List<Category> getAllCategories() {
		
		logger.info("Start request: " + Constants.GET_ALL_CATEGORY);
						
		CategoryDAO categoryDAO = appContext.getBean(CategoryDAO.class);
		List<Category> list = categoryDAO.getAll();

		for (Category c : list) {
			logger.info("Category List::" + c);
			if ( c.getMessages().size() != 0){
				logger.info("Category with messages");
			}
		}
		logger.info("End request: " + Constants.GET_ALL_CATEGORY);
		
		return list;
	}
}
