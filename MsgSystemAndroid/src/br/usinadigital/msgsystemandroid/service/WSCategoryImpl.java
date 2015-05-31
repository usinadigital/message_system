package br.usinadigital.msgsystemandroid.service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import br.usinadigital.msgsystemandroid.model.Category;
import br.usinadigital.msgsystemandroid.util.Constants;

public abstract class WSCategoryImpl extends AsyncTask<String, Void, Void> implements WSCategory{

	private String response;
	
	private String error = null;
	
	public abstract void onPreWSRequest();
	
	public abstract void onPostWSRequest();
	
	public void getAllCategories(){
		String serviceCategories = "http://192.168.0.3:8080/MsgSystemWS-0.0.1/rest/category/all";
		Log.d(Constants.TAG,"Request service: " + serviceCategories);
		execute(serviceCategories);
	}
	
	protected void onPreExecute() {
		onPreWSRequest();
	}

	protected void onPostExecute(Void unused) {
		if ( error == null ){
			
		}else{
			
		}
		onPostWSRequest();
	}
	
	protected Void doInBackground(String... urls) {

		BufferedReader reader = null;
		HttpClient httpClient = new DefaultHttpClient();
		try {
			HttpGet httpGetRequest = new HttpGet(urls[0]);
			HttpResponse httpResponse = httpClient.execute(httpGetRequest);
			HttpEntity entity = httpResponse.getEntity();

			byte[] buffer = new byte[8192];
			if (entity != null) {
				InputStream inputStream = entity.getContent();
				try {
					int bytesRead = 0;
					BufferedInputStream bis = new BufferedInputStream(
							inputStream);
					while ((bytesRead = bis.read(buffer)) != -1) {
						response = new String(buffer, 0, bytesRead);
					}
				} catch (Exception e) {
					Log.e(Constants.TAG,e.toString());
					e.printStackTrace();
					error = e.getMessage();
				} finally {
					try {
						inputStream.close();
					} catch (Exception ignore) {
					}
				}
			}
		} catch (Exception e) {
			Log.e(Constants.TAG,e.toString());
			e.printStackTrace();
			error = e.getMessage();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return null;
	}
	
	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
