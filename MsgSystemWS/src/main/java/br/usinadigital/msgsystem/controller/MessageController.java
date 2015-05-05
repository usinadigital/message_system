package br.usinadigital.msgsystem.controller;

import java.util.Date;
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

import br.usinadigital.msgsystem.dao.PersonDAO;
import br.usinadigital.msgsystem.model.Employee;
import br.usinadigital.msgsystem.model.Person;


@Controller
public class MessageController implements ServletContextAware {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	private ServletContext servletContext;
	
	@RequestMapping(value = "/rest/msg/prova", method = RequestMethod.GET)
	public @ResponseBody Person provaHibernate() {
		
		logger.info("Start hibernate test.");
		
		String urlValue = servletContext.getInitParameter("contextConfigLocation");		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(urlValue);

		PersonDAO personDAO = context.getBean(PersonDAO.class);

		Person person = new Person();
		person.setName("marco");
		person.setCountry("Italy");

		personDAO.save(person);

		logger.info("Person::" + person);

		List<Person> list = personDAO.list();

		for (Person p : list) {
			logger.info("Person List::" + p);
		}
		// close resources
		context.close();
		logger.info("Close hibernate test.");
		
		Employee emp = new Employee();
		emp.setId(9999);
		emp.setName("PROVA");
		emp.setCreatedDate(new Date());
				
		return person;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
