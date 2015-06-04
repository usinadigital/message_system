package br.usinadigital.msgsystemws.service;

import br.usinadigital.msgsystemws.model.Message;

/**
 * NOT IMPLEMENTED: Service to send text to Android phone: Google Cloud Messaging
 *
 */
public interface MessageGCM {
	
	public void send(Message message);
	
}
