package br.usinadigital.msgsystemandroid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

public class MessagesActivity extends Activity{
	
	LinearLayout linearLayout;
	MessageDAO messageDAO;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.messages);
		linearLayout = (LinearLayout) findViewById(R.id.linearLayoutMessages);

		SharedPreferences messages = getSharedPreferences(Constants.FILE_MESSAGES, Context.MODE_PRIVATE);
		SharedPreferences configurations = getSharedPreferences(Constants.FILE_CONFIGURATIONS, Context.MODE_PRIVATE);
		SharedPreferences prefName = getSharedPreferences(Constants.FILE_CATEGORY_NAME, Context.MODE_PRIVATE);
		SharedPreferences prefCheck = getSharedPreferences(Constants.FILE_CATEGORY_CHECK, Context.MODE_PRIVATE);
		
		messageDAO = new MessageDAOImpl(messages);
		ConfigurationDAO configDAO = new ConfigurationDAOImpl(configurations);
		CategoryDAO categoryDAO = new CategoryDAOImpl(prefName,prefCheck);
		
		int historyLength = 7; //configDAO.getHistoryLength();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -historyLength);
		Map<String,String> checks = categoryDAO.loadAllCheck();
		Date fromDate = cal.getTime();
		Integer[] categoriesId = Utils.toStringArray(checks.keySet());
		WSMessage wsMessage = new WSMessageImpl(getString(R.string.getMessageURL)) {
			public void onPreWSRequest() {
				Log.d(Constants.TAG, "Start Request HTTP" + getString(R.string.getMessageURL));
			}
			public void onPostWSRequest() {
				String response = getResponse();
				Log.d(Constants.TAG, "Stop Response HTTP");
				if (response == null) {
					Log.d(Constants.TAG, getString(R.string.serviceNotAvailable));
					UIUtils.showDialog(MessagesActivity.this,getString(R.string.alertTitleDialog),getString(R.string.serviceNotAvailable));
				} else {
					Message[] messages = JsonUtils.fromJsonToMessages(response);
					if (messages == null) {
						Log.d(Constants.TAG, "Error parsing Json");
						//showDialog(getString(R.string.serviceNotAvailable), getString(R.string.alertTitleDialog));
					} else {
						Log.d(Constants.TAG, "Response: " + Arrays.toString(messages));
						//addCheckboxesList(messages, null);
					}
				}
			}
		};
		wsMessage.getMessagesFromDateByCategories(fromDate,categoriesId);
	}

	private static String[] objectToString(Object[] src) {
		String[] dest = new String[src.length];
		for (int i = 0; i < src.length; i++) {
			dest[i] = (String) src[i];
		}
		return dest;
	}

	private void addCheckboxesList(Map<String, String> cat, Map<String, String> check) {
		CheckBox checkBox;
		Iterator<Entry<String, String>> it = cat.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			checkBox = new CheckBox(this);
			String id = entry.getKey().toString();
			String text = entry.getValue().toString();
			boolean checked;
			if (check == null) {
				checked = false;
			} else {
				checked = check.containsKey(id) ? true : false;
			}
			setCheckBox(checkBox, id, text, checked);
			linearLayout.addView(checkBox);
		}
	}

	View.OnClickListener getOnClickDoSomething(final Button button) {
		return new View.OnClickListener() {
			public void onClick(View v) {
				CheckBox checkBox = (CheckBox) v;
				String id = String.valueOf(button.getId());
				UIUtils.showToast(MessagesActivity.this,id);
			}
		};
	}

	private void setCheckBox(CheckBox checkBox, String id, String text, boolean checked) {
		checkBox.setId(Integer.parseInt(id));
		checkBox.setText(text);
		checkBox.setChecked(checked);
		checkBox.setOnClickListener(getOnClickDoSomething(checkBox));
	}

	
}
