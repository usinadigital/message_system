package br.usinadigital.msgsystemandroid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import br.usinadigital.msgsystemandroid.dao.CategoryDAO;
import br.usinadigital.msgsystemandroid.dao.CategoryDAOImpl;
import br.usinadigital.msgsystemandroid.dao.ConfigurationDAO;
import br.usinadigital.msgsystemandroid.dao.ConfigurationDAOImpl;
import br.usinadigital.msgsystemandroid.service.WSCategory;
import br.usinadigital.msgsystemandroid.service.WSCategoryImpl;
import br.usinadigital.msgsystemandroid.util.Constants;
import br.usinadigital.msgsystemandroid.util.JsonUtils;
import br.usinadigital.msgsystemandroid.util.UIUtils;
import br.usinadigital.msgsystemandroid.util.Utils;

public class ConfigurationsActivity extends ActionBarActivity {

	private Context context;
	private AlertDialog radioDialog;
	private ConfigurationDAO configDAO;
	CategoryDAO daoCategory;
	private String[] historyNames;
	private String[] frequencyNames;
	private int[] frequencyValues;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.configurations);
		UIUtils.setActionBarIcon(getSupportActionBar());
		context = this.getBaseContext();
		SharedPreferences configurations = getSharedPreferences(Constants.FILE_CONFIGURATIONS, Context.MODE_PRIVATE);
		SharedPreferences prefName = getSharedPreferences(Constants.FILE_CATEGORY_NAME, Context.MODE_PRIVATE);
		SharedPreferences prefCheck = getSharedPreferences(Constants.FILE_CATEGORY_CHECK, Context.MODE_PRIVATE);
		configDAO = new ConfigurationDAOImpl(configurations);
		daoCategory = new CategoryDAOImpl(prefName, prefCheck);
		historyNames = getResources().getStringArray(R.array.array_history_names);
		frequencyNames = getResources().getStringArray(R.array.array_frequency_names);
		frequencyValues = getResources().getIntArray(R.array.array_frequency_values);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void clickCategories(View v) {

		if (daoCategory.categoriesCount() != 0) {
			Map<String, String> storedCategories = daoCategory.loadAllCategories();
			Map<String, String> storedCheck = daoCategory.loadAllCheck();
			visualizeCatgoryDialog(Utils.getSortedkeys(storedCategories), storedCategories, storedCheck);
		} else {
			WSCategory wsCategory = getInstanceWSCategory();
			wsCategory.getAllCategories();
		}

	}

	public void clickHistory(View v) {
		int history = configDAO.getHistoryLength();

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(getString(R.string.configurations_history_dialog_title));
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

		Log.d(Constants.TAG, "History selected:1" + Arrays.toString(frequencyNames));
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(getString(R.string.configurations_frequency_dialog_title));
		Log.d(Constants.TAG, "History selected:12");
		builder.setSingleChoiceItems(frequencyNames, frequency, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				configDAO.setUpdateFrequency(item);
				radioDialog.dismiss();
				int freq = frequencyValues[item];
				Utils.reInitializeMessageService(context, freq);
				Log.d(Constants.TAG, "Set new frequency:" + freq);
			}
		});
		radioDialog = builder.create();
		radioDialog.show();
	}

	private void visualizeCatgoryDialog(final Integer[] sortedKeys, Map<String, String> cat, Map<String, String> check) {
		final CharSequence[] items = new CharSequence[sortedKeys.length];
		final boolean[] itemsChecked = new boolean[sortedKeys.length];
		final ArrayList<Integer> seletedItems = new ArrayList<Integer>();
		int i = 0;
		for (i = 0; i < sortedKeys.length; i++) {
			String id = sortedKeys[i].toString();
			String text = cat.get(id).toString();
			items[i] = text;
			if ( check.containsKey(id) ){
				itemsChecked[i] = true;
				seletedItems.add(sortedKeys[i]);
			}else{
				itemsChecked[i] = false;
			}
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(this.getResources().getString(R.string.configurations_categories_dialog_title));
		builder.setMultiChoiceItems(items, itemsChecked, new DialogInterface.OnMultiChoiceClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
				if (isChecked) {
					seletedItems.add(sortedKeys[indexSelected]);
				} else if (seletedItems.contains(sortedKeys[indexSelected])) {
					seletedItems.remove(Integer.valueOf(sortedKeys[indexSelected]));
				}
			}
		}).setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				Log.d(Constants.TAG, "Selected:" + seletedItems.toString());
				daoCategory.deleteAllChecks();
				daoCategory.saveChecks(seletedItems);
			}
		}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
			}
		});
		radioDialog = builder.create();
		radioDialog.show();
	}

	private WSCategory getInstanceWSCategory() {
		WSCategory wsCategory = new WSCategoryImpl(getString(R.string.getAllCategoriesURL)) {

			ProgressDialog dialog = new ProgressDialog(ConfigurationsActivity.this);

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
					UIUtils.showDialog(ConfigurationsActivity.this, getString(R.string.alertTitleDialog), getString(R.string.serviceNotAvailable));
				} else {
					Map<String, String> newCategories = JsonUtils.fromJsonToCategoryMap(response);
					Log.d(Constants.TAG, "Response Mapped: " + newCategories);
					daoCategory.deleteAllCategories();
					daoCategory.saveCategories(newCategories);
					daoCategory.refreshCheckIds();
					Date date = new Date();
					configDAO.setCategoriesLastUpdate(date);
					Map<String, String> storedCheck = daoCategory.loadAllCheck();
					Log.d(Constants.TAG, "Stored Check: " + storedCheck);
					visualizeCatgoryDialog(Utils.getSortedkeys(newCategories), newCategories, storedCheck);
				}
			}
		};
		return wsCategory;
	}
}
