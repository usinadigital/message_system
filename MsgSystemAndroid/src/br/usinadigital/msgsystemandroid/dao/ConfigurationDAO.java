package br.usinadigital.msgsystemandroid.dao;

import java.util.Date;

public interface ConfigurationDAO {

	public boolean isFirstApplicationExecution();
	
	public void unsetFirstApplicationExecution();
	
	public Date getCategoriesLastUpdate();
	
	public String getCategoriesLastUpdateToString();
	
	public void setCategoriesLastUpdate(Date date);
	
	public Date getMessagesLastUpdate();
	
	public String getMessagesLastUpdateToString();
	
	public void setMessagesLastUpdate(Date date);
	
	public int getHistoryLength(); 
	
	public void setHistoryLength(int length);
	
	public int getUpdateFrequency(); 
	
	public void setUpdateFrequency(int freq);
}
