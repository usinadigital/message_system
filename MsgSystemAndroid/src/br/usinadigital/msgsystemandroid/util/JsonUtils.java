package br.usinadigital.msgsystemandroid.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import br.usinadigital.msgsystemandroid.model.Message;

public class JsonUtils {

	/**
	 * Convert a Json array into a Mp
	 * 
	 * @param content
	 * @return
	 */
	public static Map<String, String> fromJsonToCategoryMap(String content) {
		Map<String, String> categories = new HashMap<String, String>();
		try {
			JSONArray json = new JSONArray(content);
			int lengthJsonArr = json.length();

			for (int i = 0; i < lengthJsonArr; i++) {
				JSONObject jsonChildNode = json.getJSONObject(i);
				String id = jsonChildNode.optString("id").toString();
				String name = jsonChildNode.optString("name").toString();
				categories.put(id, name);
			}
		} catch (JSONException e) {
			Log.e(Constants.TAG, "Exception while parsin the content: " + content);
			Log.e(Constants.TAG, "Exception message: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		return categories;
	}

	public static Message fromJsonToMessage(String content) {
		Message msg;
		try {
			JSONObject jsonObj = new JSONObject(content);
			String id = jsonObj.optString("id").toString();
			String title = jsonObj.optString("title").toString();
			String text = jsonObj.optString("text").toString();
			String creationdate = jsonObj.optString("creationdate").toString();
			msg = new Message(id, title, text, creationdate);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return null;
		}
		return msg;
	}

	public static Message[] fromJsonToMessages(String content) {
		List<Message> messages = new ArrayList<Message>();
		try {
			JSONArray json = new JSONArray(content);
			int lengthJsonArr = json.length();

			for (int i = 0; i < lengthJsonArr; i++) {
				JSONObject jsonChildNode = json.getJSONObject(i);
				String id = jsonChildNode.optString("id").toString();
				String title = jsonChildNode.optString("title").toString();
				String text = jsonChildNode.optString("text").toString();
				String creationdate = jsonChildNode.optString("creationdate").toString();
				Message msg = new Message(id, title, text, creationdate);
				messages.add(msg);
			}
		} catch (Exception e) {
			Log.e(Constants.TAG, "Exception while parsin the content: " + content);
			Log.e(Constants.TAG, "Exception message: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		return (Message[]) messages.toArray(new Message[messages.size()]);
	}

	public static String createJsonWSRequest(String fromDate, Integer[] categoriesId) throws JSONException {
		JSONObject json = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		for (int i = 0; i < categoriesId.length; i++)
			jsonarray.put(categoriesId[i]);
		json.put("fromDate", fromDate);
		json.put("categoriesId", jsonarray);

		return json.toString();
	}

}
