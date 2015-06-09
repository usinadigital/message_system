package br.usinadigital.msgsystemandroid;

import br.usinadigital.msgsystemandroid.util.Constants;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MessageViewActivity extends Activity {

	private TextView txtTitle;
	private TextView txtBody;
	String title, text;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.messages);
		txtTitle = (TextView) findViewById(R.id.txtTitle);
		txtBody = (TextView) findViewById(R.id.txtBody);
		Bundle b = getIntent().getExtras();
		if (b != null){
			title = b.getString(Constants.MESSAGE_TITLE);
			text = b.getString(Constants.MESSAGE_TEXT);
		}
		
		
	}
}
