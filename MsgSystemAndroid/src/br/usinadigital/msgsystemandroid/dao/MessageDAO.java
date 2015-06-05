package br.usinadigital.msgsystemandroid.dao;

import java.util.Map;
import java.util.Set;

import br.usinadigital.msgsystemandroid.model.Message;

public interface MessageDAO {

	public Map<String,Set<String>> getAll();
	
	public void save(Message[] msgs);
	
}
