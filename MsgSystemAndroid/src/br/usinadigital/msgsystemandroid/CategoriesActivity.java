package br.usinadigital.msgsystemandroid;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;
import br.usinadigital.msgsystemandroid.dao.DAOCategory;
import br.usinadigital.msgsystemandroid.dao.DAOCategoryImpl;
import br.usinadigital.msgsystemandroid.service.WSCategory;
import br.usinadigital.msgsystemandroid.service.WSCategoryImpl;
import br.usinadigital.msgsystemandroid.util.Constants;
import br.usinadigital.msgsystemandroid.util.Util;

public class CategoriesActivity extends Activity {

	static final String[] FRUITS = new String[] { "Apple", "Avocado", "Banana", "Blueberry", "Coconut", "Durian", "Guava", "Kiwifruit", "Jackfruit", "Mango", "Olive", "Pear", "Sugar-apple" };

	LinearLayout linearCategories;
	SharedPreferences prefName;
	SharedPreferences prefCheck;
	DAOCategory daoCategory;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.categories);
		linearCategories = (LinearLayout) findViewById(R.id.linearCategories);
		
		prefName = getSharedPreferences(Constants.CATEGORY_NAME, Context.MODE_PRIVATE);
		prefCheck = getSharedPreferences(Constants.CATEGORY_CHECK, Context.MODE_PRIVATE);
		daoCategory = new DAOCategoryImpl(prefName,prefCheck);
		Log.d(Constants.TAG, "Stored values:\n" + daoCategory.toString());
		
		// The first time that I open the categories list it´s empty. It´s after the installation
		if (prefName.getAll().size() == 0) {
			WSCategory wsCategory = new WSCategoryImpl(getString(R.string.getAllCategoriesURL)) {
				public void onPreWSRequest() {
					Log.d(Constants.TAG, "Pre HTTP Request");
				}
				public void onPostWSRequest() {
					String response = getResponse();
					Log.d(Constants.TAG, "Post HgetResponse()TTP Request");
					if (response == null) {
						showDialog(getString(R.string.serviceNotAvailable), getString(R.string.alertTitleDialog));
					} else {
						Map<String, String> fromWSCat = Util.fromJsonToCategoryMap(response);
						Log.d(Constants.TAG, "Response Mapped: " + fromWSCat);
						daoCategory.saveCategories(fromWSCat);
						addCheckboxesList(fromWSCat,null);
					}
				}
			};
			wsCategory.getAllCategories();
		} else {	// The categories were before loaded 
			Map<String, String> storedCategories = daoCategory.loadAllCategories();
			Map<String, String> storedCheck = daoCategory.loadAllCheck();
			addCheckboxesList(storedCategories,storedCheck);
		}	
	}

	private static String[] objectToString(Object[] src) {
		String[] dest = new String[src.length];
		for (int i = 0; i < src.length; i++) {
			dest[i] = (String) src[i];
		}
		return dest;
	}

	private void addCheckboxesList(Map<String, String> cat, Map<String, String> check) {
		CheckBox checkBox;
		Iterator<Entry<String, String>> it = cat.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			checkBox = new CheckBox(this);
			String id = entry.getKey().toString();
			String text = entry.getValue().toString();
			boolean checked;
			if (check == null){
				checked = false;
			} else {
				checked = check.containsKey(id) ? true : false;
			}
			setCheckBox(checkBox, id, text, checked);
			linearCategories.addView(checkBox);
		}
	}

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
	
	private void showDialog(String message, String title) {
		AlertDialog alertDialog = new AlertDialog.Builder(CategoriesActivity.this).create();
		alertDialog.setTitle(title);
		alertDialog.setMessage(message);
		alertDialog.setIcon(R.drawable.ic_launcher);

		alertDialog.setButton(1, "OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});

		alertDialog.show();
	}

	private void showToast(String message) {
		Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
		toast.show();
	}
}
