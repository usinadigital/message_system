package br.usinadigital.msgsystemws.dao;

import java.util.Date;
import java.util.List;

import br.usinadigital.msgsystemws.model.Category;
import br.usinadigital.msgsystemws.model.Message;

public interface MessageDAO {
	
	// Maybe not necessary
	public void save(Message msg);
	
	public void send(Message msg);
    
    public List<Message> getAll();
    
    public List<Message> getMessagesFromDateByCategories(Date fromDate, List<Category> categories);
}
