package br.usinadigital.msgsystemws;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.usinadigital.msgsystemws.dao.MessageDAO;
import br.usinadigital.msgsystemws.model.Category;
import br.usinadigital.msgsystemws.model.Message;
import br.usinadigital.msgsystemws.util.Utils;

public class JdbcSpringTest {

	private static final Logger logger = LoggerFactory.getLogger(JdbcSpringTest.class);

	public static void getMessagesFromDateByCategoriesTest() throws ParseException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
		MessageDAO messageDAO = context.getBean(MessageDAO.class);
		int[] cats = new int[] { 10, 11, 12 };

		Date data = Utils.fromStringToDate("01-01-2015 15:50:00");
		List<Message> msgs = messageDAO.getMessagesFromDateByCategories(data, cats);

		for (Message msg : msgs) {
			logger.info(msg.toString());
		}
		context.close();

		logger.info("Done");
	}

	public static void saveTest() {
		Message msg = new Message();
		msg.setTitle("titolo");
		msg.setText("testo");
		Category cat1 = new Category(10);
		Category cat2 = new Category(11);
		Category cat3 = new Category(12);
		Set<Category> cats = new HashSet<Category>();
		cats.add(cat1);
		cats.add(cat2);
		cats.add(cat3);
		msg.setCategories(cats);
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
		MessageDAO messageDAO = context.getBean(MessageDAO.class);
		messageDAO.save(msg);
		context.close();
	}

	public static void main(String[] args) throws ParseException {
		getMessagesFromDateByCategoriesTest();
	}
}
