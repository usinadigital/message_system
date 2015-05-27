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

public class JdbcSpringTest {

	private static final Logger logger = LoggerFactory.getLogger(JdbcSpringTest.class);

	public static void prova()throws ParseException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
        MessageDAO messageDAO = context.getBean(MessageDAO.class);
        
        Category cat1 = new Category(10);
        Category cat2 = new Category(11);
        Category cat3 = new Category(12);
        List<Category> cats = new ArrayList<Category>();
        cats.add(cat1);
        cats.add(cat2);
        cats.add(cat3);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date data = sdf.parse("01/05/2015");
        List<Message> msgs = messageDAO.getMessagesFromDateByCategories(data, cats);
//      List<Message> msgs = messageDAO.list();
        for (Message msg : msgs) {
        	logger.info(msg.toString());
//        	if ( msg.getCategories().size() != 0){
//				logger.info("************* Message with categories");
//			}
        }
        context.close();
        
		logger.info("Done");
	}
	
	public static void updateTest(){
		Message msg = new Message();
		Category cat1 = new Category(10);
        Category cat2 = new Category(11);
        Category cat3 = new Category(12);
        Set<Category> cats = new HashSet<Category>();
        cats.add(cat1);
        cats.add(cat2);
        cats.add(cat3);
        msg.setCategories(cats);
		msg.setText("pippotesto");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
        MessageDAO messageDAO = context.getBean(MessageDAO.class);
        messageDAO.save(msg);
        context.close();
	}
	
	public static void main(String[] args) throws ParseException {
		updateTest();
	}
}
