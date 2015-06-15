package br.usinadigital.msgsystemandroid.service;

import java.io.BufferedInputStream;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.os.AsyncTask;
import android.util.Log;
import br.usinadigital.msgsystemandroid.util.Constants;

@SuppressWarnings( "deprecation" )
public abstract class GetAllCategoryWSImpl extends AsyncTask<String, Void, Void> implements GetAllCategoryWS{

	private String serviceURL;
	
	private String response;
	
	private String error = null;
	
	public abstract void onPreWSRequest();
	
	public abstract void onPostWSRequest();
	
	public GetAllCategoryWSImpl(String uri){
		this.serviceURL = uri;
	}
	
	public void getAllCategories(){
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
        HttpClient httpClient = new DefaultHttpClient(myParams);
		try {
			
			HttpGet httpGetRequest = new HttpGet(urls[0]);
			HttpResponse httpResponse = httpClient.execute(httpGetRequest);
			HttpEntity entity = httpResponse.getEntity();

			byte[] buffer = new byte[4096];
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
