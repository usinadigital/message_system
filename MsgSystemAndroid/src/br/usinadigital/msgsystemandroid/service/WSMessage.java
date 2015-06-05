package br.usinadigital.msgsystemandroid.service;

import java.util.Date;

public interface WSMessage {

	public void getMessagesFromDateByCategories(Date fromDate, Integer[] categoriesId);
}
