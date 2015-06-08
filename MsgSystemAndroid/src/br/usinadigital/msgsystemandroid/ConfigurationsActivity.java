package br.usinadigital.msgsystemandroid;

import java.util.Arrays;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import br.usinadigital.msgsystemandroid.dao.ConfigurationDAO;
import br.usinadigital.msgsystemandroid.dao.ConfigurationDAOImpl;
import br.usinadigital.msgsystemandroid.util.Constants;

public class ConfigurationsActivity extends Activity {

	final Context context = this;
	AlertDialog radioDialog;
	ConfigurationDAO configDAO;
	String[] historyNames;
	String[] frequencyNames;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.configurations);
		SharedPreferences configurations = getSharedPreferences(Constants.FILE_CONFIGURATIONS, Context.MODE_PRIVATE);
		configDAO = new ConfigurationDAOImpl(configurations);
		historyNames = getResources().getStringArray(R.array.array_history_names);
		frequencyNames = getResources().getStringArray(R.array.array_frequency_names);
	}

	public void clickCategories(View v) {
		Intent intent = new Intent(context, CategoriesActivity.class);
		startActivity(intent);
	}

	public void clickHistory(View v) {
		int history = configDAO.getHistoryLength();
			
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(getString(R.string.configurations_history_selection));
		builder.setSingleChoiceItems(historyNames, history, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				configDAO.setHistoryLength(item);
				radioDialog.dismiss();
				Log.d(Constants.TAG, "History selected:" + item); 
			}
		});
		radioDialog = builder.create();
		radioDialog.show();
	}

	public void clickFrequency(View v) {
		Log.d(Constants.TAG, "History selected:0"); 
		int frequency = configDAO.getUpdateFrequency();
	
		Log.d(Constants.TAG, "History selected:1"+Arrays.toString(frequencyNames)); 
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(getString(R.string.configurations_frequency_selection));
		Log.d(Constants.TAG, "History selected:12"); 
		builder.setSingleChoiceItems(frequencyNames, frequency, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				configDAO.setUpdateFrequency(item);
				radioDialog.dismiss();
				Log.d(Constants.TAG, "Frequency selected:" + item); 
			}
		});
		radioDialog = builder.create();
		radioDialog.show();
	}
	
}
