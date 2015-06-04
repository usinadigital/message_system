package br.usinadigital.msgsystemws.dao;

import java.util.Date;
import java.util.List;

import br.usinadigital.msgsystemws.model.Category;
import br.usinadigital.msgsystemws.model.Message;

public interface MessageDAO {
	
	// return the number of categories saved
	public int save(Message msg);
    
    public List<Message> getAll();
    
    public List<Message> getMessagesFromDateByCategories(Date fromDate, int[] categoriesId);
}
