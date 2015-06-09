package br.usinadigital.msgsystemandroid;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import android.app.ProgressDialog;
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
import br.usinadigital.msgsystemandroid.service.WSCategory;
import br.usinadigital.msgsystemandroid.service.WSCategoryImpl;
import br.usinadigital.msgsystemandroid.service.WSMessage;
import br.usinadigital.msgsystemandroid.service.WSMessageImpl;
import br.usinadigital.msgsystemandroid.util.Constants;
import br.usinadigital.msgsystemandroid.util.JsonUtils;
import br.usinadigital.msgsystemandroid.util.MessageUtils;
import br.usinadigital.msgsystemandroid.util.UIUtils;
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
		
		Log.d(Constants.TAG,"Start MessageService");
		Toast.makeText(this, "Start MessageService", Toast.LENGTH_SHORT).show();
		Message[] messages = messageDAO.getAll();
		Log.d(Constants.TAG, "Stored values:\n" + Arrays.toString(messages));
        
		// update the categories from the WS
		WSCategory wsCategory = getInstanceWSCategory();
		wsCategory.getAllCategories();
		
		// update the messages from the WS
        WSMessage wsMessage = getInstanceWSMessage();
		String fromDate = configDAO.getMessagesLastUpdateToString();
		Integer[] categoriesIds = MessageUtils.getSelectedCategories(categoryDAO);
		wsMessage.getMessagesFromDateByCategories(fromDate, categoriesIds);
		
		Log.d(Constants.TAG,"Stop MessageService");
		
        return START_STICKY;
    }
	
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
    
    private WSMessage getInstanceWSMessage() {
		WSMessage wsMessage = new WSMessageImpl(getString(R.string.getMessageURL)) {
			public void onPreWSRequest() {
				Log.d(Constants.TAG, "Start Request HTTP " + getString(R.string.getMessageURL));
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
						Log.d(Constants.TAG, "----------------------------------------- Response: " + Arrays.toString(newMessages));
//						Message[] newMessagesFiltered = MessageUtils.deleteOldMessagesFromHistory(newMessages,context,configDAO);
//						messageDAO.save(newMessagesFiltered);
						messageDAO.save(newMessages);
						Date date = new Date();
						configDAO.setMessagesLastUpdate(date);
					}
				}
			}
		};
		return wsMessage;
	}
    
    private WSCategory getInstanceWSCategory(){
		WSCategory wsCategory = new WSCategoryImpl(getString(R.string.getAllCategoriesURL)) {
			
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
   

}
