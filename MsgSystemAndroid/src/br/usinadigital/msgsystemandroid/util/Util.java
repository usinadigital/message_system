package br.usinadigital.msgsystemandroid.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.SharedPreferences;


public class Util {
	
	public static Map<String,String> fromJsonToCategoryMap(String content) {
		String OutputData = "";
		Map<String,String> categories = new HashMap<String,String>();
		
		try {
			JSONArray json = new JSONArray(content);
			int lengthJsonArr = json.length();

			for (int i = 0; i < lengthJsonArr; i++) {
				JSONObject jsonChildNode = json.getJSONObject(i);
				String id = jsonChildNode.optString("id").toString();
				String name = jsonChildNode.optString("name").toString();
				categories.put(id,name);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return categories;
	}
	
	/*
	 * 	 * Put the checked categories saved in android to the new categories list got from the WS
	 */
	public static Set<String> updateCategories(Map<String,String> newCat, Set<String> oldChecked){
		Set<String> newChecked = new HashSet<String>();
		for( String id: oldChecked){
			if (newCat.containsKey(id)){
				newChecked.add(id);
			}
		}
		return newChecked;
	}
	
}
