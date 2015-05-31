package br.usinadigital.msgsystemandroid.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Util {
	
	public static String readJson(String content) {
		String OutputData = "";
		try {

			JSONArray json = new JSONArray(content);
			int lengthJsonArr = json.length();

			for (int i = 0; i < lengthJsonArr; i++) {
				JSONObject jsonChildNode = json.getJSONObject(i);
				String name = jsonChildNode.optString("name").toString();
				OutputData += "Category=" + name + "\n";
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return OutputData;
	}
	
}
