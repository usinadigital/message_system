package br.usinadigital.msgsystemandroid;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.TextView;
import br.usinadigital.msgsystemandroid.R;
import br.usinadigital.msgsystemandroid.util.Constants;
import br.usinadigital.msgsystemandroid.util.UIUtils;

public class MessageViewActivity extends ActionBarActivity {

	private TextView txtDate;
	private TextView txtTitle;
	private TextView txtBody;
	String date, title, text;
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message);
		UIUtils.styleActionBar(getSupportActionBar(), getResources().getString(R.color.actionBarBg)); 
		txtDate = (TextView)findViewById(R.id.txtDate);
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtBody = (TextView)findViewById(R.id.txtBody);
		Bundle b = getIntent().getExtras();
		if (b != null){
			date = b.getString(Constants.MESSAGE_DATE);
			title = b.getString(Constants.MESSAGE_TITLE);
			text = b.getString(Constants.MESSAGE_TEXT);
		}
		txtDate.setText(date);
		txtTitle.setText(title);
		txtBody.setText(text);
	}
}
