package br.usinadigital.msgsystemandroid;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.TextView;
import br.usinadigital.msgsystemandroid.R;
import br.usinadigital.msgsystemandroid.util.Constants;
import br.usinadigital.msgsystemandroid.util.UIUtils;

public class MessageViewActivity extends ActionBarActivity {

	private TextView txtTitle;
	private TextView txtBody;
	String title, text;
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message);
		UIUtils.setActionBarIcon(getSupportActionBar()); 
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtBody = (TextView)findViewById(R.id.txtBody);
		Bundle b = getIntent().getExtras();
		if (b != null){
			title = b.getString(Constants.MESSAGE_TITLE);
			text = b.getString(Constants.MESSAGE_TEXT);
		}
		
		txtTitle.setText(title);
		txtBody.setText(text);
	}
}
