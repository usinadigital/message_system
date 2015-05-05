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

import br.usinadigital.msgsystemws.dao.CategoryDAO;
import br.usinadigital.msgsystemws.model.Category;
import br.usinadigital.msgsystemws.util.Constants;
 
@Controller
public class CategoryController implements ServletContextAware {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	private ServletContext servletContext;
	
	@RequestMapping(value = Constants.GET_TEST_CATEGORY, method = RequestMethod.GET)
	public @ResponseBody Category getTestCategorie() {
		
		logger.info("Begin request test category");
		
		Category cat = new Category();
		cat.setId(0);
		cat.setName("test_name");
		cat.setDescription("test_desc");
		cat.setValid(0);
		
		logger.info("End request test category");
		
		return cat;
	}
	
	@RequestMapping(value = Constants.GET_ALL_CATEGORY, method = RequestMethod.GET)
	public @ResponseBody List<Category> getAllCategories() {
		
		logger.info("Requesting all categories");
		
		String urlValue = servletContext.getInitParameter("contextConfigLocation");		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(urlValue);
		
		CategoryDAO categoryDAO = context.getBean(CategoryDAO.class);
		List<Category> list = categoryDAO.list();

		for (Category c : list) {
			logger.info("Category List::" + c);
		}
		
		context.close();
		logger.info("Request closed.");
		
		Category cat = new Category();
		cat.setId(10);
		cat.setName("dummyname");
		cat.setDescription("dummydesc");
		cat.setValid(1);
		
		return list;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
