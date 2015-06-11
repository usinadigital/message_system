package br.usinadigital.msgsystemandroid.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

import android.os.AsyncTask;
import android.util.Log;
import br.usinadigital.msgsystemandroid.util.Constants;
import br.usinadigital.msgsystemandroid.util.JsonUtils;

@SuppressWarnings("deprecation")
public abstract class GetMessagesFromDateByCategoriesWSImpl extends AsyncTask<String, Void, Void> implements GetMessagesFromDateByCategoriesWS {

	private String serviceURL;
	
	private String fromDate;
	
	Integer[] categoriesId;
	
	private String response = null;

	private String error = null;

	public abstract void onPreWSRequest();

	public abstract void onPostWSRequest();

	public GetMessagesFromDateByCategoriesWSImpl(String uri) {
		this.serviceURL = uri;
	}

	public void getMessagesFromDateByCategories(String fromDate, Integer[] categoriesId) {
		this.fromDate = fromDate;
		this.categoriesId = categoriesId;
		execute(serviceURL);
	}

	protected void onPreExecute() {
		onPreWSRequest();
	}

	protected void onPostExecute(Void unused) {
		onPostWSRequest();
	}

	protected Void doInBackground(String... urls) {

		HttpParams myParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(myParams, Constants.HTTP_TIMEOUT_MILLISEC);
        HttpConnectionParams.setSoTimeout(myParams, Constants.HTTP_TIMEOUT_MILLISEC);
        HttpClient httpclient = new DefaultHttpClient(myParams);
        HttpResponse httpresponse;
        
		try {
			Log.d(Constants.TAG, "fromDate: " + fromDate);
			Log.d(Constants.TAG, "categoriesId: " + Arrays.toString(categoriesId));
			String jsonParam = JsonUtils.createJsonWSRequest(fromDate, categoriesId);
			Log.d(Constants.TAG, "Request Json param: " + jsonParam);
			
			HttpPost httppost = new HttpPost(urls[0]);
			httppost.setHeader("Content-type", "application/json");
			
            StringEntity entity = new StringEntity(jsonParam);  
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            httppost.setEntity(entity);
            
            httpresponse = httpclient.execute(httppost);

			if (entity != null) {
				InputStream is = httpresponse.getEntity().getContent();
				try {
					StringBuilder sb = new StringBuilder();
					BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	                String line = null;
	                while((line = reader.readLine()) != null){
	                   sb.append(line);
	                }	
	                response = sb.toString();
				} catch (Exception e) {
					Log.e(Constants.TAG, e.toString());
					e.printStackTrace();
					error = e.getMessage();
				} finally {
					try {
						is.close();
					} catch (Exception ignore) {

					}
				}
			}
		} catch (Exception e) {
			Log.e(Constants.TAG, e.toString());
			e.printStackTrace();
			error = e.getMessage();
		} finally {
			httpclient.getConnectionManager().shutdown();
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
