package br.usinadigital.msgsystemws.dao;
 
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.usinadigital.msgsystemws.model.Category;

public class CategoryDAOImpl implements CategoryDAO{
	private SessionFactory sessionFactory;
	 
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
     
    public void save(Category p) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(p);
        tx.commit();
        session.close();
    }
 
    @SuppressWarnings("unchecked")
    public List<Category> list() {
        Session session = this.sessionFactory.openSession();
        List<Category> categoryList = session.createQuery("from Category").list();
        session.close();
        return categoryList;
    }
}
