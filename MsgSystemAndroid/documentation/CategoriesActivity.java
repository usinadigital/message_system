package br.usinadigital.msgsystemandroid;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import br.usinadigital.msgsystemandroid.service.WSCategory;
import br.usinadigital.msgsystemandroid.service.WSCategoryImpl;
import br.usinadigital.msgsystemandroid.util.Constants;

public class CategoriesActivity extends ListActivity{
	
	static final String[] FRUITS = new String[] { "Apple", "Avocado", "Banana",
		"Blueberry", "Coconut", "Durian", "Guava", "Kiwifruit",
		"Jackfruit", "Mango", "Olive", "Pear", "Sugar-apple" };
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
 	
		SharedPreferences sharedpreferences = getSharedPreferences(Constants.PREF_CATEGORIES, Context.MODE_PRIVATE);
		if ( sharedpreferences == null){
			WSCategory wsCategory = new WSCategoryImpl(){
				public void onPreWSRequest(){
					
				}
				public void onPostWSRequest(){
					
				}
			};
			wsCategory.getAllCategories();
		}
				
		setListAdapter(new ArrayAdapter<String>(this, R.layout.categories,FRUITS));
 
		ListView listView = getListView();
		listView.setTextFilterEnabled(true);
 
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Log.d(Constants.TAG,((TextView) view).getText().toString());
			    Toast.makeText(getApplicationContext(),
				((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}
		});
 
	}
	
	
	public void showToast(String message){
		Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
		toast.show();
	}
}
