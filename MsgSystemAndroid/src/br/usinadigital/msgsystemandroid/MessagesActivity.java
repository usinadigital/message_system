package br.usinadigital.msgsystemandroid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import br.usinadigital.msgsystemandroid.R;
import br.usinadigital.msgsystemandroid.dao.ConfigurationDAO;
import br.usinadigital.msgsystemandroid.dao.ConfigurationDAOImpl;
import br.usinadigital.msgsystemandroid.dao.MessageDAO;
import br.usinadigital.msgsystemandroid.dao.MessageDAOImpl;
import br.usinadigital.msgsystemandroid.model.Message;
import br.usinadigital.msgsystemandroid.util.Constants;
import br.usinadigital.msgsystemandroid.util.MessageUtils;
import br.usinadigital.msgsystemandroid.util.UIUtils;
import br.usinadigital.msgsystemandroid.util.Utils;

public class MessagesActivity extends ActionBarActivity {

	private Context context;
	private ListView mainListView;
	private SimpleAdapter sa;
	private MessageDAO messageDAO;
	private ConfigurationDAO configDAO;
	
	ArrayList<HashMap<String, String>> listMessages = new ArrayList<HashMap<String, String>>();

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.messages);
		UIUtils.setActionBarIcon(getSupportActionBar()); 
		context = this.getBaseContext();
		mainListView = (ListView) findViewById(R.id.mainListView);
		initialiseList();

		SharedPreferences msgTitle = getSharedPreferences(Constants.FILE_MESSAGE_TITLE, Context.MODE_PRIVATE);
		SharedPreferences msgText = getSharedPreferences(Constants.FILE_MESSAGE_TEXT, Context.MODE_PRIVATE);
		SharedPreferences msgDate = getSharedPreferences(Constants.FILE_MESSAGE_DATE, Context.MODE_PRIVATE);
		SharedPreferences configurations = getSharedPreferences(Constants.FILE_CONFIGURATIONS, Context.MODE_PRIVATE);
		messageDAO = new MessageDAOImpl(msgTitle, msgText, msgDate);
		configDAO = new ConfigurationDAOImpl(configurations);
		Log.d(Constants.TAG, "Stored messages:\n" + Arrays.toString(messageDAO.getAll()));
		Message[] storedfilteredMessages = MessageUtils.deleteOldMessagesFromHistory(messageDAO.getAll(), this, configDAO);
		Log.d(Constants.TAG, "Filtered messages:\n" + Arrays.toString(storedfilteredMessages));
		messageDAO.deleteAll();
		clearList();
		MessageUtils.orderArray(storedfilteredMessages);
		Log.d(Constants.TAG, "Ordered messages:\n" + Arrays.toString(storedfilteredMessages));
		messageDAO.save(storedfilteredMessages);
		populateList(storedfilteredMessages);
	}

	private void clearList() {
		listMessages.clear();
		sa.notifyDataSetChanged();
	}

	private void populateList(Message[] messages) {
		for (int i = 0; i < messages.length; i++) {
			listMessages.add(convertMessagesToMap(messages[i]));
		}
		sa.notifyDataSetChanged();
	}

	private void initialiseList() {
		sa = new SimpleAdapter(this, listMessages, R.layout.custom_listview, new String[] { Constants.FIRST_LINE, Constants.SECOND_LINE }, new int[] { R.id.firstline, R.id.secondline });
		mainListView.setAdapter(sa);
		mainListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Map<String,String> itemValue = (Map<String,String>) mainListView.getItemAtPosition(position);
				Log.d(Constants.TAG,"context="+context.toString());
				Intent intent = new Intent(context, MessageViewActivity.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            intent.putExtra(Constants.MESSAGE_DATE, itemValue.get(Constants.MESSAGE_DATE));
	            intent.putExtra(Constants.MESSAGE_TITLE, itemValue.get(Constants.MESSAGE_TITLE));
	            intent.putExtra(Constants.MESSAGE_TEXT, itemValue.get(Constants.MESSAGE_TEXT));
	            context.startActivity(intent);
			}
		});
	}

	private HashMap<String, String> convertMessagesToMap(Message message) {
		Map<String, String> map = new HashMap<String, String>();
		Date data = Utils.stringToDate(message.getCreationdate());
		String localeFormattedDate = Utils.dateToStringLocale(data);
		map.put(Constants.FIRST_LINE, message.getTitle());
		map.put(Constants.SECOND_LINE, localeFormattedDate);
		map.put(Constants.MESSAGE_DATE, localeFormattedDate);
		map.put(Constants.MESSAGE_TITLE,  message.getTitle());
		map.put(Constants.MESSAGE_TEXT,  message.getText());

		return (HashMap<String, String>) map;
	}
}
