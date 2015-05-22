package br.usinadigital.msgsystemwebapp.service;

import br.usinadigital.msgsystemwebapp.model.Category;
import br.usinadigital.msgsystemwebapp.model.Message;

public interface WSManager {
	
	// Se utente deve fare il pooling al servizio
	public void save(Message message);
	
	// se faccio il push del msg all utente
	public void sendMessageByCategories(Message message);
	
	public Category[] getAllCategories();
}
