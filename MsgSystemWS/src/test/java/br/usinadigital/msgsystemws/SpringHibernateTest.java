package br.usinadigital.msgsystemws;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.usinadigital.msgsystemws.model.Category;
import br.usinadigital.msgsystemws.model.Message;

public class SpringHibernateTest {

	private static final Logger logger = LoggerFactory
			.getLogger(SpringHibernateTest.class);

	public static void main(String[] args) {

		System.out.println("Hibernate many to many (XML Mapping)");
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		Message msg = new Message();
		msg.setText("Ciao testo!");
		
		Category category1 = new Category("nomecat1");
		Category category2 = new Category("nomecat2");

		Set<Category> categories = new HashSet<Category>();
		categories.add(category1);
		categories.add(category2);

		msg.setCategories(categories);

		session.save(msg);

		session.getTransaction().commit();
		System.out.println("Done");

	}
}
