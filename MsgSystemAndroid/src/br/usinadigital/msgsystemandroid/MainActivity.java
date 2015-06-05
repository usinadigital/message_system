package br.usinadigital.msgsystemandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import br.usinadigital.msgsystemandroid.util.Constants;

public class MainActivity extends Activity {

	final Context context = this;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Log.d(Constants.TAG,"URL="+getResources().getString(R.string.getMessageURL));
		
		final Button cat = (Button) findViewById(R.id.menu_messages);
		cat.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(context, MessagesActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.mymenu, menu);

		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		Intent intent;

		switch (item.getItemId()) {

		case R.id.categories:
			showToast("Clicked Categories");
			intent = new Intent(context, CategoriesActivity.class);
			startActivity(intent);
			return true;

		case R.id.updates:
			showToast("Clicked Updates");
			intent = new Intent(context, CategoriesActivity.class);
			startActivity(intent);
			return true;

		default:
			return super.onOptionsItemSelected(item);

		}
	}

	
	
	public void showToast(String message) {
		Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
		toast.show();
	}

}