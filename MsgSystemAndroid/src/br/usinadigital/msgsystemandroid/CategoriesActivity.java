package br.usinadigital.msgsystemandroid;

import java.util.Date;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
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

public class CategoriesActivity extends Activity {

	LinearLayout linearLayout;
	CategoryDAO daoCategory;
	ConfigurationDAO configDAO;
	Button btUpdate;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.categories);
		btUpdate = (Button)findViewById(R.id.btUpdateCategories);
		linearLayout = (LinearLayout) findViewById(R.id.linearLayoutCategories);
		
		SharedPreferences prefName = getSharedPreferences(Constants.FILE_CATEGORY_NAME, Context.MODE_PRIVATE);
		SharedPreferences prefCheck = getSharedPreferences(Constants.FILE_CATEGORY_CHECK, Context.MODE_PRIVATE);
		SharedPreferences configurations = getSharedPreferences(Constants.FILE_CONFIGURATIONS, Context.MODE_PRIVATE);
		daoCategory = new CategoryDAOImpl(prefName,prefCheck);
		configDAO = new ConfigurationDAOImpl(configurations);
		setButtonText(configDAO.getCategoriesLastUpdate());
		Log.d(Constants.TAG, "Stored values:\n" + daoCategory.toString());
		
		if (daoCategory.categoriesCount() != 0) {
			Map<String, String> storedCategories = daoCategory.loadAllCategories();
			Map<String, String> storedCheck = daoCategory.loadAllCheck();
			addCheckboxesList(Utils.getSortedkeys(storedCategories),storedCategories,storedCheck);
		} else {	
			WSCategory wsCategory = getInstanceWSCategory();
			wsCategory.getAllCategories();
		}	
	}

	
	public void clickUpdateCategories(View v) {
		daoCategory.deleteAllCategories();
		WSCategory wsCategory = getInstanceWSCategory();
		wsCategory.getAllCategories();
	}
	
	private WSCategory getInstanceWSCategory(){
		WSCategory wsCategory = new WSCategoryImpl(getString(R.string.getAllCategoriesURL)) {
			
			ProgressDialog dialog = new ProgressDialog(CategoriesActivity.this);
			
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
					UIUtils.showDialog(CategoriesActivity.this,getString(R.string.alertTitleDialog),getString(R.string.serviceNotAvailable));
				} else {
					Map<String, String> newCategories = JsonUtils.fromJsonToCategoryMap(response);
					Log.d(Constants.TAG, "Response Mapped: " + newCategories);
					daoCategory.saveCategories(newCategories);
					daoCategory.refreshCheckIds();
					Date date = new Date();
					configDAO.setCategoriesLastUpdate(date);
					setButtonText(date);
					Map<String, String> storedCheck = daoCategory.loadAllCheck();
					Log.d(Constants.TAG, "Stored Check: " + storedCheck);
					addCheckboxesList(Utils.getSortedkeys(newCategories), newCategories, storedCheck);					
				}
			}
		};
		return wsCategory;
	}
	
	private void setButtonText(Date date) {
		Date data = configDAO.getCategoriesLastUpdate();
		btUpdate.setText(UIUtils.printOn2lineWithDate(getString(R.string.categories_update),getString(R.string.lastUpdate),data));
		
	}
	
	private void addCheckboxesList(List<Integer> sortedKeys, Map<String, String> cat, Map<String, String> check) {
		CheckBox checkBox;
		if(((LinearLayout) linearLayout).getChildCount() > 0) 
		    ((LinearLayout) linearLayout).removeAllViews(); 
		for (Integer key : sortedKeys) {
			checkBox = new CheckBox(this);
			String id = key.toString();
			String text = cat.get(id).toString();
			boolean checked;
			checked = check.containsKey(id) ? true : false;
			setCheckBox(checkBox, id, text, checked);
			linearLayout.addView(checkBox);
		}
	}

	//	When you click on the checkbox
	View.OnClickListener getOnClickDoSomething(final Button button) {
		return new View.OnClickListener() {
			public void onClick(View v) {
				CheckBox checkBox = (CheckBox)v;
				String id = String.valueOf(button.getId());
				daoCategory.saveCheckById(id,checkBox.isChecked());
			}
		};
	}

	private void setCheckBox(CheckBox checkBox, String id, String text, boolean checked){
		checkBox.setId(Integer.parseInt(id));
		checkBox.setText(text);
		checkBox.setChecked(checked);
		checkBox.setOnClickListener(getOnClickDoSomething(checkBox));
	}
	
}
