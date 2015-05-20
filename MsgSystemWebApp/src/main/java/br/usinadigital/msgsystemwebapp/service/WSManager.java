package br.usinadigital.msgsystemwebapp.service;

import br.usinadigital.msgsystemwebapp.model.Category;
import br.usinadigital.msgsystemwebapp.model.Message;

public interface WSManager {
	
	public void sendMessageToUsersByCategories(Message message);
	
	public Category[] getAllCategories();
}
