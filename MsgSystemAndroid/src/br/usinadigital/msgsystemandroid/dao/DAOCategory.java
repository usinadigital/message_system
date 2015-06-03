package br.usinadigital.msgsystemandroid.dao;

import java.util.Map;

public interface DAOCategory {

	public void saveCheckById(String id, boolean checked);
	
	public void deleteAllCategories();
	
	public void saveCategories(Map<String,String> categoriesMap);

	public Map<String,String> loadAllCategories();
	
	public Map<String,String> loadAllCheck();
}
