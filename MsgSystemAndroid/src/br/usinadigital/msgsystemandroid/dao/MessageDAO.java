package br.usinadigital.msgsystemandroid.dao;

import java.util.Map;
import java.util.Set;

import br.usinadigital.msgsystemandroid.model.Message;

public interface MessageDAO {

	public Message[] getAll();
	
	public void deleteAll();
	
	public void save(Message[] msgs);
	
}
