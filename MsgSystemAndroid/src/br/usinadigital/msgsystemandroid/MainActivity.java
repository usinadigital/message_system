package br.usinadigital.msgsystemandroid;

import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import br.usinadigital.msgsystemandroid.dao.ConfigurationDAO;
import br.usinadigital.msgsystemandroid.dao.ConfigurationDAOImpl;
import br.usinadigital.msgsystemandroid.util.Constants;
import br.usinadigital.msgsystemandroid.util.Utils;

public class MainActivity extends Activity {

	Context context;
	ConfigurationDAO configDAO;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		context = this;
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

	private void initializeConfiguration() {
		int[] frequencyValues = getResources().getIntArray(R.array.array_frequency_values);
		configDAO = new ConfigurationDAOImpl(getSharedPreferences(Constants.FILE_CONFIGURATIONS, Context.MODE_PRIVATE));
		if (configDAO.isFirstApplicationExecution()) {
			Log.d(Constants.TAG, "First Application Execution");
			configDAO.unsetFirstApplicationExecution();
			configDAO.setHistoryLength(Constants.DEFAULT_CONFIGURATION_HISTORY);
			configDAO.setUpdateFrequency(Constants.DEFAULT_CONFIGURATION_FREQUENCY);
			configDAO.setMessagesLastUpdate(new Date());
			configDAO.setCategoriesLastUpdate(new Date());
		} else {
			Log.d(Constants.TAG, "NOT First Application Execution");
		}
		Utils.initializeMessageService(this, frequencyValues[configDAO.getUpdateFrequency()]);
	}

}
