package br.usinadigital.msgsystemwebapp.service;

import br.usinadigital.msgsystemwebapp.model.Category;
import br.usinadigital.msgsystemwebapp.model.Message;

public interface WSManager {
	
	public void save(Message message);
	
	public Category[] getAllCategories();
}
