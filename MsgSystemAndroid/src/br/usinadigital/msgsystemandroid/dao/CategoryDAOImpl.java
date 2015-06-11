package br.usinadigital.msgsystemandroid.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import br.usinadigital.msgsystemandroid.util.Constants;

public class CategoryDAOImpl implements CategoryDAO{
	
	SharedPreferences categories;
	SharedPreferences categoriesCheck;
	
	public CategoryDAOImpl(SharedPreferences prefName, SharedPreferences prefCheck) {
		super();
		this.categories = prefName;
		this.categoriesCheck = prefCheck;
	}

	public void saveCheckById(String id, boolean checked){
		Editor edCheck = categoriesCheck.edit();
		if ( checked ){
			edCheck.putString(id, Constants.CHECKED_STATE);
		}else{
			edCheck.remove(id);
		}
		edCheck.commit();
	}
	
	public void deleteAllCategories(){
		Editor editor =  categories.edit();
		editor.clear();
		editor.commit();
	}
	
	public void deleteAllChecks(){
		Editor editor =  categoriesCheck.edit();
		editor.clear();
		editor.commit();
	}
	
	public void saveChecks(List<Integer> list){
		Editor edCheck = categoriesCheck.edit();
		for (Integer val : list) {
			edCheck.putString(val.toString(), Constants.CHECKED_STATE);
		}
		edCheck.commit();
	}
	
	/*
	 * Remove all check that are not present in the catgories list.
	 * It happend when I get a new categories list.
	 * */
	public void refreshCheckIds(){
		Editor editor =  categoriesCheck.edit();
		Map<String, ?> allEntries = categoriesCheck.getAll();
		for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
			String key = String.valueOf(entry.getKey());
			if (!categories.contains(key)){
				editor.remove(key);
			} 
		}
		editor.commit();
	}
	
	public int categoriesCount(){
		return categories.getAll().size();
	}
	
	public void saveCategories(Map<String,String> categoriesMap){
		Editor editor =  categories.edit();
		for (Map.Entry<String, ?> entry : categoriesMap.entrySet()) {
			String key = String.valueOf(entry.getKey());
			String name = entry.getValue().toString();
			editor.putString(key, name);
		}
		editor.commit();
	}
	
	public Map<String,String> loadAllCategories(){
		Map<String,String> res = new HashMap<String,String>();
		res = (Map<String,String>)categories.getAll();
		
		return res;
	}
	
	public Map<String,String> loadAllCheck(){
		Map<String,String> res = new HashMap<String,String>();
		res = (Map<String,String>)categoriesCheck.getAll();
		
		return res;
	}
	
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		
		Map<String, ?> allEntries = categories.getAll();
		for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
			String key = entry.getKey();
			String name = entry.getValue().toString();
			str.append("Category: ").append(key).append(", ")
				.append(name).append(", checked=");
			if (categoriesCheck.contains(key)){
				str.append(Constants.CHECKED_STATE);
			}else {
				str.append(Constants.UNCHECKED_STATE);
			}
			str.append("\n");
		}
		return str.toString();
	}
	
}
