package br.usinadigital.msgsystemandroid.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JsonUtils {

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
			Log.e(Constants.TAG, "Exception while parsin the content: " + content);
			Log.e(Constants.TAG, "Exception message: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		return categories;
	}
	
	public static String createJsonWSRequest(Date fromDate, int[] categoriesId) throws JSONException{
		JSONObject json = new JSONObject();
		JSONArray jsonarray = new JSONArray();
	    for (int i = 0; i < categoriesId.length; i++) jsonarray.put(categoriesId[i]);
		json.put("fromDate", Utils.dateToString(fromDate));
        json.put("categoriesId", jsonarray.toString());
        
		return null;
	}
	
}
