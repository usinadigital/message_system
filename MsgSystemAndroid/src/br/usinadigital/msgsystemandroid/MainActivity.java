package br.usinadigital.msgsystemandroid;

import java.util.Date;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import br.usinadigital.msgsystemandroid.dao.CategoryDAO;
import br.usinadigital.msgsystemandroid.dao.CategoryDAOImpl;
import br.usinadigital.msgsystemandroid.dao.ConfigurationDAO;
import br.usinadigital.msgsystemandroid.dao.ConfigurationDAOImpl;
import br.usinadigital.msgsystemandroid.service.WSCategory;
import br.usinadigital.msgsystemandroid.service.WSCategoryImpl;
import br.usinadigital.msgsystemandroid.util.Constants;
import br.usinadigital.msgsystemandroid.util.JsonUtils;
import br.usinadigital.msgsystemandroid.util.UIUtils;

public class MainActivity extends Activity {

	final Context context = this;
	ConfigurationDAO configDAO;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initializeConfiguration();
	}

	public void clickMessages(View v) {
		Intent intent = new Intent(context, MessagesActivity.class);
		startActivity(intent);
	}
	
	public void clickConfigurations(View v) {
		Intent intent = new Intent(context, ConfigurationsActivity.class);
		startActivity(intent);
	}
	
	private void initializeConfiguration(){
		configDAO = new ConfigurationDAOImpl(getSharedPreferences(Constants.FILE_CONFIGURATIONS, Context.MODE_PRIVATE));
		if (configDAO.isFirstApplicationExecution()){
			Log.d(Constants.TAG,"First Application Execution");
			configDAO.unsetFirstApplicationExecution();
			configDAO.setHistoryLength(Constants.DEFAULT_CONFIGURATION_HISTORY);
			configDAO.setUpdateFrequency(Constants.DEFAULT_CONFIGURATION_FREQUENCY);
			configDAO.setMessagesLastUpdate(new Date());
			configDAO.setCategoriesLastUpdate(new Date());
		}else{
			Log.d(Constants.TAG,"NOT First Application Execution");
		}
	}
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//
//		MenuInflater inflater = getMenuInflater();
//		inflater.inflate(R.menu.mymenu, menu);
//
//		return true;
//
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//
//		Intent intent;
//
//		switch (item.getItemId()) {
//
//		case R.id.categories:
//			showToast("Clicked Categories");
//			intent = new Intent(context, CategoriesActivity.class);
//			startActivity(intent);
//			return true;
//
//		case R.id.updates:
//			showToast("Clicked Updates");
//			intent = new Intent(context, CategoriesActivity.class);
//			startActivity(intent);
//			return true;
//
//		default:
//			return super.onOptionsItemSelected(item);
//
//		}
//	}
	
}
