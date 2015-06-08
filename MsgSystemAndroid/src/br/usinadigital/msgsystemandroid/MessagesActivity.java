package br.usinadigital.msgsystemandroid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import br.usinadigital.msgsystemandroid.dao.CategoryDAO;
import br.usinadigital.msgsystemandroid.dao.CategoryDAOImpl;
import br.usinadigital.msgsystemandroid.dao.ConfigurationDAO;
import br.usinadigital.msgsystemandroid.dao.ConfigurationDAOImpl;
import br.usinadigital.msgsystemandroid.dao.MessageDAO;
import br.usinadigital.msgsystemandroid.dao.MessageDAOImpl;
import br.usinadigital.msgsystemandroid.model.Message;
import br.usinadigital.msgsystemandroid.service.WSMessage;
import br.usinadigital.msgsystemandroid.service.WSMessageImpl;
import br.usinadigital.msgsystemandroid.util.Constants;
import br.usinadigital.msgsystemandroid.util.JsonUtils;
import br.usinadigital.msgsystemandroid.util.UIUtils;
import br.usinadigital.msgsystemandroid.util.Utils;

public class MessagesActivity extends Activity {

	ListView mainListView;
	private SimpleAdapter sa;
	MessageDAO messageDAO;
	ConfigurationDAO configDAO;
	CategoryDAO categoryDAO;
	Button btUpdate;

	ArrayList<HashMap<String, String>> listMessages = new ArrayList<HashMap<String, String>>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.messages);
		btUpdate = (Button) findViewById(R.id.btUpdateMessages);
		mainListView = (ListView) findViewById(R.id.mainListView);
		initialiseList();

		SharedPreferences msgTitle = getSharedPreferences(Constants.FILE_MESSAGE_TITLE, Context.MODE_PRIVATE);
		SharedPreferences msgText = getSharedPreferences(Constants.FILE_MESSAGE_TEXT, Context.MODE_PRIVATE);
		SharedPreferences msgDate = getSharedPreferences(Constants.FILE_MESSAGE_DATE, Context.MODE_PRIVATE);
		SharedPreferences configurations = getSharedPreferences(Constants.FILE_CONFIGURATIONS, Context.MODE_PRIVATE);
		SharedPreferences prefName = getSharedPreferences(Constants.FILE_CATEGORY_NAME, Context.MODE_PRIVATE);
		SharedPreferences prefCheck = getSharedPreferences(Constants.FILE_CATEGORY_CHECK, Context.MODE_PRIVATE);
		messageDAO = new MessageDAOImpl(msgTitle, msgText, msgDate);
		configDAO = new ConfigurationDAOImpl(configurations);
		categoryDAO = new CategoryDAOImpl(prefName, prefCheck);
		setButtonText(configDAO.getMessagesLastUpdate());
		Log.d(Constants.TAG, "Stored values:\n" + messageDAO.toString());
		Message[] messages = deleteOldMessagesFromHistory(messageDAO.getAll());
		populateList(messages);
	}

	public void clickUpdateMessages(View v) {
		WSMessage wsMessage = getInstanceWSMessage();
		String fromDate = configDAO.getMessagesLastUpdateToString();
		Integer[] categoriesIds = getSelectedCategories();
		wsMessage.getMessagesFromDateByCategories(fromDate, categoriesIds);
	}

	private WSMessage getInstanceWSMessage() {
		WSMessage wsMessage = new WSMessageImpl(getString(R.string.getMessageURL)) {
			public void onPreWSRequest() {
				Log.d(Constants.TAG, "Start Request HTTP" + getString(R.string.getMessageURL));
			}

			public void onPostWSRequest() {
				String response = getResponse();
				Log.d(Constants.TAG, "Stop Response HTTP");
				if (response == null) {
					Log.d(Constants.TAG, getString(R.string.serviceNotAvailable));
					UIUtils.showDialog(MessagesActivity.this, getString(R.string.alertTitleDialog), getString(R.string.serviceNotAvailable));
				} else {
					Message[] newMessages = JsonUtils.fromJsonToMessages(response);
					if (newMessages == null) {
						Log.d(Constants.TAG, "Error parsing Json");
						UIUtils.showDialog(MessagesActivity.this, getString(R.string.alertTitleDialog), getString(R.string.serviceNotAvailable));
					} else {
						Log.d(Constants.TAG, "Response: " + Arrays.toString(newMessages));
						Date date = new Date();
						configDAO.setMessagesLastUpdate(date);
						setButtonText(date);
						Message[] updatedMessages = deleteOldMessagesFromHistory(newMessages);
						messageDAO.save(updatedMessages);
						populateList(updatedMessages);
					}
				}
			}
		};
		return wsMessage;
	}

	private void setButtonText(Date date) {
		Date data = configDAO.getMessagesLastUpdate();
		btUpdate.setText(UIUtils.printOn2lineWithDate(getString(R.string.messages_update), getString(R.string.lastUpdate), data));

	}

	private Date getHistoryDate() {
		int[] historyValues = getResources().getIntArray(R.array.array_history_values);
		int historyLength = historyValues[configDAO.getHistoryLength()];
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		cal.add(Calendar.DATE, - historyLength);
		return cal.getTime();
	}

	private Message[] deleteOldMessagesFromHistory(Message[] messages) {
		Date lowerLimit = getHistoryDate();
		Log.d(Constants.TAG, "Lower History Limit: " + Utils.dateToString(lowerLimit));
		List<Message> list = new ArrayList<Message>();
		for (Message msg : messages) {
			Log.d(Constants.TAG,"Msg Date: " + msg.getCreationdate());
			if (Utils.stringToDate(msg.getCreationdate()).after(lowerLimit)) {
				Log.d(Constants.TAG,"Added"); 
				list.add(msg);
			}
		}
		return list.toArray(new Message[list.size()]);
	}

	private Integer[] getSelectedCategories() {
		Map<String, String> checks = categoryDAO.loadAllCheck();
		return Utils.toStringArray(checks.keySet());
	}

	private void orderArray(Message[] messages) {
		Arrays.sort(messages, new Comparator<Message>() {
			public int compare(Message m1, Message m2) {
				Date d1 = Utils.stringToDate(m1.getCreationdate());
				Date d2 = Utils.stringToDate(m2.getCreationdate());
				return d1.compareTo(d2);
			}
		});
	}

	private void populateList(Message[] messages) {
		//orderArray(messages);
		for (int i = 0; i < messages.length; i++) {
			listMessages.add(convertMessagesToMap(messages[i]));
		}
		sa.notifyDataSetChanged();
	}

	private void initialiseList() {
		sa = new SimpleAdapter(this, listMessages, android.R.layout.two_line_list_item, new String[] { Constants.FIRST_LINE, Constants.SECOND_LINE }, new int[] { android.R.id.text1,
				android.R.id.text2 });
		mainListView.setAdapter(sa);
		mainListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				int itemPosition = position;
				Map itemValue = (Map) mainListView.getItemAtPosition(position);
				Toast.makeText(getApplicationContext(), "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG).show();
			}
		});
	}

	private HashMap<String, String> convertMessagesToMap(Message message) {
		Map<String, String> map = new HashMap<String, String>();
		map.put(Constants.FIRST_LINE, message.getTitle());
		map.put(Constants.SECOND_LINE, message.getCreationdate());

		return (HashMap<String, String>) map;
	}
}
