package br.usinadigital.msgsystemandroid.dao;

import java.text.ParseException;
import java.util.Date;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import br.usinadigital.msgsystemandroid.R;
import br.usinadigital.msgsystemandroid.util.Constants;
import br.usinadigital.msgsystemandroid.util.Utils;

public class ConfigurationDAOImpl implements ConfigurationDAO {

	SharedPreferences configuration;

	public ConfigurationDAOImpl(SharedPreferences configuration) {
		super();
		this.configuration = configuration;
	}

	public Date getCategoriesLastUpdate() {
		String value = configuration.getString(Constants.CONFIGURATION_LAST_UPDATE_CATEGORIES, null);
		return Utils.stringToDate(value);
	}

	public String getCategoriesLastUpdateToString() {
		String value = configuration.getString(Constants.CONFIGURATION_LAST_UPDATE_CATEGORIES, null);
		return value;
	}
	
	public void setCategoriesLastUpdate(Date date) {
		Editor editor = configuration.edit();
		String value = Utils.dateToString(date);
		editor.putString(Constants.CONFIGURATION_LAST_UPDATE_CATEGORIES, value);
		editor.commit();
	}

	public Date getMessagesLastUpdate() {
		String value = configuration.getString(Constants.CONFIGURATION_LAST_UPDATE_MESSAGES, null);
		return Utils.stringToDate(value);
	}

	public String getMessagesLastUpdateToString() {
		String value = configuration.getString(Constants.CONFIGURATION_LAST_UPDATE_MESSAGES, null);
		return value;
	}
	
	public void setMessagesLastUpdate(Date date) {
		Editor editor = configuration.edit();
		String value = Utils.dateToString(date);
		editor.putString(Constants.CONFIGURATION_LAST_UPDATE_MESSAGES, value);
		editor.commit();
	}

	public int getHistoryLength() {
		int value = configuration.getInt(Constants.CONFIGURATION_HISTORY, -1);
		return value;
	}

	public void setHistoryLength(int length) {
		Editor editor = configuration.edit();
		editor.putInt(Constants.CONFIGURATION_HISTORY, length);
		editor.commit();
	}

	public int getUpdateFrequency() {
		int value = configuration.getInt(Constants.CONFIGURATION_FREQUENCY, -1);
		return value;
	}

	public void setUpdateFrequency(int freq) {
		Editor editor = configuration.edit();
		editor.putInt(Constants.CONFIGURATION_FREQUENCY, freq);
		editor.commit();
	}

	public boolean isFirstApplicationExecution() {
		boolean value = configuration.getBoolean(Constants.CONFIGURATION_FIRST_EXECUTION, true);
		return value;
	}

	public void unsetFirstApplicationExecution() {
		Editor editor = configuration.edit();
		editor.putBoolean(Constants.CONFIGURATION_FIRST_EXECUTION, false);
		editor.commit();
	}
}
