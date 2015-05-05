package br.usinadigital.msgsystem;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.usinadigital.msgsystem.dao.PersonDAO;
import br.usinadigital.msgsystem.model.Person;

public class SpringHibernateTest {

	private static final Logger logger = LoggerFactory.getLogger(SpringHibernateTest.class);
	
	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"application-config.xml");

		PersonDAO personDAO = context.getBean(PersonDAO.class);

		Person person = new Person();
		person.setName("Pankaj");
		person.setCountry("India");

		personDAO.save(person);

		logger.info("Person::" + person);

		List<Person> list = personDAO.list();

		for (Person p : list) {
			logger.info("Person List::" + p);
		}
		// close resources
		context.close();
	}
}
