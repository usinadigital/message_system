package br.usinadigital.msgsystemandroid;

import java.util.Iterator;
import java.util.Map;

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
	DAOCategory category;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.categories);
		linearCategories = (LinearLayout) findViewById(R.id.linearCategories);
		
		prefName = getSharedPreferences(Constants.CATEGORY_NAME, Context.MODE_PRIVATE);
		prefCheck = getSharedPreferences(Constants.CATEGORY_CHECK, Context.MODE_PRIVATE);
		category = new DAOCategoryImpl(prefName,prefCheck);
		
		// The first time that I open the categories list it´s empty
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
						Map<String, String> newCat = Util.fromJsonToCategoryMap(response);
						Log.d(Constants.TAG, "Response Mapped: " + newCat);
						addCheckboxesList(newCat);
					}
				}
			};
			wsCategory.getAllCategories();
		}
	}

	public static String[] objectToString(Object[] src) {
		String[] dest = new String[src.length];
		for (int i = 0; i < src.length; i++) {
			dest[i] = (String) src[i];
		}
		return dest;
	}

	public void addCheckboxesList(Map<String, String> newCat) {
		Log.d(Constants.TAG, "Names: " + newCat.values().toArray().toString());
		CheckBox checkBox;
		Iterator it = newCat.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			checkBox = new CheckBox(this);
			checkBox.setId(Integer.parseInt(entry.getKey().toString()));
			checkBox.setText(entry.getValue().toString());
			checkBox.setOnClickListener(getOnClickDoSomething(checkBox));
			linearCategories.addView(checkBox);
		}
	}

	View.OnClickListener getOnClickDoSomething(final Button button) {
		return new View.OnClickListener() {
			public void onClick(View v) {
				CheckBox checkBox = (CheckBox)v;
				String id = String.valueOf(button.getId());
				String text = button.getText().toString();
				Log.d(Constants.TAG,"id=" + id + ", name=" + text);
				category.saveState(id,checkBox.isChecked());
				category.printData();
			}
		};
	}

	
	public void showDialog(String message, String title) {
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

	public void showToast(String message) {
		Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
		toast.show();
	}
}
