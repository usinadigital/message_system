package br.usinadigital.msgsystemandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import br.usinadigital.msgsystemandroid.service.WSCategory;
import br.usinadigital.msgsystemandroid.service.WSCategoryImpl;
import br.usinadigital.msgsystemandroid.util.Constants;

public class MainActivity extends Activity {

	final Context context = this;

	//TextView uiOutput = (TextView) findViewById(R.id.output);
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		WSCategory wsCategory = new WSCategoryImpl(){
		public void onPreWSRequest(){
			Log.d(Constants.TAG, "Pre HTTP Request");
		}
		public void onPostWSRequest(){
			Log.d(Constants.TAG, "Post HTTP Request");
			if (getError() != null) {
				
				
			} else {
				
				
			}
		}
	};
	
		wsCategory.getAllCategories();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.mymenu, menu);
		
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case R.id.category:
			showToast("Clicked Configuration");
			 Intent intent = new Intent(context, CategoriesActivity.class);
             startActivity(intent); 
			return true;

		default:
			return super.onOptionsItemSelected(item);

		}
	}

	public void showToast(String message){
		Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
		toast.show();
	}

}