package br.usinadigital.msgsystemws.dao;

import java.util.List;

import br.usinadigital.msgsystemws.model.Category;
import br.usinadigital.msgsystemws.model.Message;

public interface MessageDAO {
	public void save(Category c);
    
    public List<Message> list();
    
    public void send(Message msg);
}
