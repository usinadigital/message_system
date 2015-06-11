package br.usinadigital.msgsystemandroid.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import android.content.Context;
import android.util.Log;
import br.usinadigital.msgsystemandroid.R;
import br.usinadigital.msgsystemandroid.dao.CategoryDAO;
import br.usinadigital.msgsystemandroid.dao.ConfigurationDAO;
import br.usinadigital.msgsystemandroid.model.Message;

public class MessageUtils {

	public static  Integer[] getSelectedCategories(CategoryDAO categoryDAO) {
		Map<String, String> checks = categoryDAO.loadAllCheck();
		return Utils.toStringArray(checks.keySet());
	}

	public static Date getHistoryDate(Context context, ConfigurationDAO configDAO) {
		int[] historyValues = context.getResources().getIntArray(R.array.array_history_values);
		int historyLength = historyValues[configDAO.getHistoryLength()];
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		cal.add(Calendar.DATE, -historyLength);
		return cal.getTime();
	}

	public static Message[] deleteOldMessagesFromHistory(Message[] messages, Context context, ConfigurationDAO configDAO) {
		Date lowerLimit = getHistoryDate(context, configDAO);
		Log.d(Constants.TAG, "Lower History Limit: " + Utils.dateToString(lowerLimit));
		List<Message> list = new ArrayList<Message>();
		for (Message msg : messages) {
			Log.d(Constants.TAG, "Msg: " + msg.toString());
			if (Utils.stringToDate(msg.getCreationdate()).after(lowerLimit)) {
				Log.d(Constants.TAG, "Added");
				list.add(msg);
			}
		}
		return list.toArray(new Message[list.size()]);
	}
	
	public static void orderArray(Message[] messages) {
		Arrays.sort(messages, new Comparator<Message>() {
			public int compare(Message m1, Message m2) {
				Date d1 = Utils.stringToDate(m1.getCreationdate());
				Date d2 = Utils.stringToDate(m2.getCreationdate());
				return d1.compareTo(d2);
			}
		});
	}
	
	public static void inversOrderArray(Message[] messages) {
		Arrays.sort(messages, new Comparator<Message>() {
			public int compare(Message m1, Message m2) {
				Date d1 = Utils.stringToDate(m1.getCreationdate());
				Date d2 = Utils.stringToDate(m2.getCreationdate());
				return -d1.compareTo(d2);
			}
		});
	}

}
