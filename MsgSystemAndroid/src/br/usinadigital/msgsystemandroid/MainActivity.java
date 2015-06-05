package br.usinadigital.msgsystemandroid;

import java.util.Date;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import br.usinadigital.msgsystemandroid.dao.CategoryDAO;
import br.usinadigital.msgsystemandroid.dao.CategoryDAOImpl;
import br.usinadigital.msgsystemandroid.dao.ConfigurationDAO;
import br.usinadigital.msgsystemandroid.dao.ConfigurationDAOImpl;
import br.usinadigital.msgsystemandroid.service.WSCategory;
import br.usinadigital.msgsystemandroid.service.WSCategoryImpl;
import br.usinadigital.msgsystemandroid.util.Constants;
import br.usinadigital.msgsystemandroid.util.JsonUtils;

public class MainActivity extends Activity {

	final Context context = this;

	CategoryDAO daoCategory;
	ConfigurationDAO configDAO;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initializeConfiguration();
		
		final Button cat = (Button) findViewById(R.id.menu_messages);
		cat.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(context, MessagesActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.mymenu, menu);

		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		Intent intent;

		switch (item.getItemId()) {

		case R.id.categories:
			showToast("Clicked Categories");
			intent = new Intent(context, CategoriesActivity.class);
			startActivity(intent);
			return true;

		case R.id.updates:
			showToast("Clicked Updates");
			intent = new Intent(context, CategoriesActivity.class);
			startActivity(intent);
			return true;

		default:
			return super.onOptionsItemSelected(item);

		}
	}

	
	private void initializeConfiguration(){
		configDAO = new ConfigurationDAOImpl(getSharedPreferences(Constants.FILE_CONFIGURATIONS, Context.MODE_PRIVATE));
		
		if (true) {
		//if (configDAO.isFirstApplicationExecution()){
			Log.d(Constants.TAG,"First Application Execution");
			configDAO.unsetFirstApplicationExecution();
			configDAO.setHistoryLength(Constants.DEFAULT_CONFIGURATION_HISTORY);
			configDAO.setUpdateFrequency(Constants.DEFAULT_CONFIGURATION_FREQUENCY);
			configDAO.setMessagessLastUpdate(new Date());
			
			SharedPreferences prefName = getSharedPreferences(Constants.FILE_CATEGORY_NAME, Context.MODE_PRIVATE);
			SharedPreferences prefCheck = getSharedPreferences(Constants.FILE_CATEGORY_CHECK, Context.MODE_PRIVATE);
			daoCategory = new CategoryDAOImpl(prefName,prefCheck);
			WSCategory wsCategory = new WSCategoryImpl(getString(R.string.getAllCategoriesURL)) {
				
				ProgressDialog dialog = new ProgressDialog(MainActivity.this);
				
				public void onPreWSRequest() {
					Log.d(Constants.TAG, "Start HTTP Request" + getString(R.string.getAllCategoriesURL));
					dialog.setMessage(getString(R.string.loading));
		            dialog.show();
				}
				public void onPostWSRequest() {
					dialog.dismiss();
					String response = getResponse();
					Log.d(Constants.TAG, "Stop Http Request");
					if (response == null) {
						showDialog( getString(R.string.alertTitleDialog),getString(R.string.serviceNotAvailable));
					} else {
						Map<String, String> categories = JsonUtils.fromJsonToCategoryMap(response);
						Log.d(Constants.TAG, "Response Mapped: " + categories);
						daoCategory.saveCategories(categories);
						configDAO.setCategoriesLastUpdate(new Date());
					}
				}
			};
			wsCategory.getAllCategories();
		}else{
			Log.d(Constants.TAG,"NOT First Application Execution");
		}
	}
	public void showDialog(String title, String message){
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle(title);
		alertDialog.setMessage(message);
		alertDialog.setIcon(R.drawable.ic_launcher);
		
		alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		
		
		alertDialog.show();
	}
	
	public void showToast(String message) {
		Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
		toast.show();
	}

}