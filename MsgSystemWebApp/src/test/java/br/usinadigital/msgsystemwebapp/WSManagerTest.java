package br.usinadigital.msgsystemwebapp;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.usinadigital.msgsystemwebapp.model.Category;
import br.usinadigital.msgsystemwebapp.model.Message;
import br.usinadigital.msgsystemwebapp.service.WSManager;

public class WSManagerTest {

	public static void messageBuilder(Message msg){
		Category cat1 = new Category(10);
        Category cat2 = new Category(11);
        Category cat3 = new Category(12);
        Set<Category> cats = new HashSet<Category>();
        cats.add(cat1);
        cats.add(cat2);
        cats.add(cat3);
        msg.setCategories(cats);
		msg.setText("pippotesto");
	}
	public static void main(String[] args) throws ParseException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/dispatcher-context.xml");
		WSManager manager = context.getBean(WSManager.class);
        
		Message msg = new Message();
		messageBuilder(msg);
		manager.sendMessageByCategories(msg);
		context.close();
	}
}
