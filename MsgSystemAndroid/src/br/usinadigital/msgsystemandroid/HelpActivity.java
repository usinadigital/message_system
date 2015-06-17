package br.usinadigital.msgsystemandroid;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import br.usinadigital.msgsystemandroid.util.UIUtils;

public class HelpActivity extends ActionBarActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);
		UIUtils.styleActionBar(getSupportActionBar(), getResources().getString(R.color.actionBarBg));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
