package br.usinadigital.msgsystemandroid.dao;

import java.util.List;
import java.util.Map;

public interface CategoryDAO {

	public void saveCheckById(String id, boolean checked);
	
	public void deleteAllCategories();
	
	public void deleteAllChecks();
	
	public void saveChecks(List<Integer> list);
	
	public void refreshCheckIds();
	
	public int categoriesCount();
	
	public void saveCategories(Map<String,String> categoriesMap);

	public Map<String,String> loadAllCategories();
	
	public Map<String,String> loadAllCheck();
}
