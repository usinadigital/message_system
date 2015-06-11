package br.usinadigital.msgsystemandroid;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import br.usinadigital.msgsystemandroid.dao.CategoryDAO;
import br.usinadigital.msgsystemandroid.dao.CategoryDAOImpl;
import br.usinadigital.msgsystemandroid.dao.ConfigurationDAO;
import br.usinadigital.msgsystemandroid.dao.ConfigurationDAOImpl;
import br.usinadigital.msgsystemandroid.dao.MessageDAO;
import br.usinadigital.msgsystemandroid.dao.MessageDAOImpl;
import br.usinadigital.msgsystemandroid.model.Message;
import br.usinadigital.msgsystemandroid.service.GetLastMessageWS;
import br.usinadigital.msgsystemandroid.service.GetLastMessageWSImpl;
import br.usinadigital.msgsystemandroid.service.GetAllCategoryWS;
import br.usinadigital.msgsystemandroid.service.GetAllCategoryWSImpl;
import br.usinadigital.msgsystemandroid.service.GetMessagesFromDateByCategoriesWS;
import br.usinadigital.msgsystemandroid.service.GetMessagesFromDateByCategoriesWSImpl;
import br.usinadigital.msgsystemandroid.util.Constants;
import br.usinadigital.msgsystemandroid.util.JsonUtils;
import br.usinadigital.msgsystemandroid.util.MessageUtils;
import br.usinadigital.msgsystemandroid.util.NotificationHelper;
import br.usinadigital.msgsystemandroid.util.Utils;

public class MessageService extends Service {

	final private Context context = this;

	MessageDAO messageDAO;
	ConfigurationDAO configDAO;
	CategoryDAO categoryDAO;

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(Constants.TAG, "MessageService scheduled");
		SharedPreferences msgTitle = getSharedPreferences(Constants.FILE_MESSAGE_TITLE, Context.MODE_PRIVATE);
		SharedPreferences msgText = getSharedPreferences(Constants.FILE_MESSAGE_TEXT, Context.MODE_PRIVATE);
		SharedPreferences msgDate = getSharedPreferences(Constants.FILE_MESSAGE_DATE, Context.MODE_PRIVATE);
		SharedPreferences configurations = getSharedPreferences(Constants.FILE_CONFIGURATIONS, Context.MODE_PRIVATE);
		SharedPreferences prefName = getSharedPreferences(Constants.FILE_CATEGORY_NAME, Context.MODE_PRIVATE);
		SharedPreferences prefCheck = getSharedPreferences(Constants.FILE_CATEGORY_CHECK, Context.MODE_PRIVATE);
		messageDAO = new MessageDAOImpl(msgTitle, msgText, msgDate);
		configDAO = new ConfigurationDAOImpl(configurations);
		categoryDAO = new CategoryDAOImpl(prefName, prefCheck);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		Log.d(Constants.TAG, "Start MessageService");
		if (getResources().getString(R.string.serviceToast).equalsIgnoreCase(Constants.ENABLED)) {
			Toast.makeText(this, "Start MessageService", Toast.LENGTH_SHORT).show();
		}
		if (!Utils.isNetworkConnected(context)) {
			Log.d(Constants.TAG, "No network connection");
		} else {
			Message[] messages = messageDAO.getAll();
			Log.d(Constants.TAG, "Stored messages:\n" + Arrays.toString(messages));

			// update the categories from the WS
			GetAllCategoryWS wsCategory = instanceGetAllCategoryWS();
			wsCategory.getAllCategories();

			// if is the first execution set the lastUpdateDate from the last
			// message in the DB
			if (configDAO.getMessagesLastUpdate() == null) {
				GetLastMessageWS ws = instanceGetLastMessageWS();
				ws.getLastMessage();
			}

			// update the messages from the WS
			GetMessagesFromDateByCategoriesWS wsMessage = instanceGetMessagesFromDateByCategoriesWS();
			String fromDate = configDAO.getMessagesLastUpdateToString();
			if (fromDate != null) {
				Integer[] categoriesIds = MessageUtils.getSelectedCategories(categoryDAO);
				wsMessage.getMessagesFromDateByCategories(fromDate, categoriesIds);
			}
		}
		Log.d(Constants.TAG, "Stop MessageService");

		return START_STICKY;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	private GetMessagesFromDateByCategoriesWS instanceGetMessagesFromDateByCategoriesWS() {
		GetMessagesFromDateByCategoriesWS wsMessage = new GetMessagesFromDateByCategoriesWSImpl(getString(R.string.getFromDateByCategoriesURL)) {
			public void onPreWSRequest() {
				Log.d(Constants.TAG, "Start Request HTTP " + getString(R.string.getFromDateByCategoriesURL));
			}

			public void onPostWSRequest() {
				String response = getResponse();
				Log.d(Constants.TAG, "Stop Response HTTP");
				if (response == null) {
					Log.d(Constants.TAG, getString(R.string.serviceNotAvailable));
				} else {
					Message[] newMessages = JsonUtils.fromJsonToMessages(response);
					if (newMessages == null) {
						Log.d(Constants.TAG, "Error parsing Json");
					} else {
						Log.d(Constants.TAG, "------------------------------ Response: " + Arrays.toString(newMessages));
						if (newMessages.length != 0) {
							MessageUtils.invertedOrderArray(newMessages);
							sendNotifications(newMessages);
							messageDAO.save(newMessages);
							String lastMessageDate = newMessages[0].getCreationdate();
							configDAO.setMessagesLastUpdate(Utils.stringToDate(lastMessageDate));
						}
					}
				}
			}
		};
		return wsMessage;
	}

	private GetLastMessageWS instanceGetLastMessageWS() {
		GetLastMessageWS wsMessage = new GetLastMessageWSImpl(getString(R.string.getLastInsertedMessageURL)) {
			public void onPreWSRequest() {
				Log.d(Constants.TAG, "Start Request HTTP " + getString(R.string.getLastInsertedMessageURL));
			}

			public void onPostWSRequest() {
				String response = getResponse();
				Log.d(Constants.TAG, "Stop Response HTTP");
				if (response == null) {
					Log.d(Constants.TAG, getString(R.string.serviceNotAvailable));
				} else {
					Message lastMessage = JsonUtils.fromJsonToMessage(response);
					if (lastMessage == null) {
						Log.d(Constants.TAG, "Error parsing Json");
					} else {
						Log.d(Constants.TAG, "Response:\n" + lastMessage.toString());
						Date date = Utils.stringToDate(lastMessage.getCreationdate());
						configDAO.setMessagesLastUpdate(date);
						Log.d(Constants.TAG, "Inserted the first sync message date: " + lastMessage.getCreationdate());
					}
				}
			}
		};
		return wsMessage;
	}

	private GetAllCategoryWS instanceGetAllCategoryWS() {
		GetAllCategoryWS wsCategory = new GetAllCategoryWSImpl(getString(R.string.getAllCategoriesURL)) {

			public void onPreWSRequest() {
				Log.d(Constants.TAG, "Start HTTP Request" + getString(R.string.getAllCategoriesURL));
			}

			public void onPostWSRequest() {
				String response = getResponse();
				Log.d(Constants.TAG, "Stop Http Request");
				if (response == null) {
				} else {
					Map<String, String> newCategories = JsonUtils.fromJsonToCategoryMap(response);
					Log.d(Constants.TAG, "New Categories: " + newCategories);
					categoryDAO.deleteAllCategories();
					categoryDAO.saveCategories(newCategories);
					categoryDAO.refreshCheckIds();
					Date date = new Date();
					configDAO.setCategoriesLastUpdate(date);
					Log.d(Constants.TAG, "Categories Saved with success");
				}
			}
		};
		return wsCategory;
	}

	private void sendNotifications(Message[] messages) {
		for (int i = messages.length - 1; i != 0; i--) {
			Log.d(Constants.TAG, "Start notification");
			int id = Integer.valueOf(messages[i].getId());
			String title = messages[i].getTitle();
			String text = messages[i].getText();
			Date data = Utils.stringToDate(messages[i].getCreationdate());
			Utils.dateToStringLocale(data);
			NotificationHelper.notify(context, id, title, text);
			Log.d(Constants.TAG, "Stop notification");
		}
	}

}
