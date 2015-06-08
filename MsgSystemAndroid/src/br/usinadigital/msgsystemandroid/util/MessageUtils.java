package br.usinadigital.msgsystemandroid.util;

import java.util.ArrayList;
import java.util.Calendar;
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
			Log.d(Constants.TAG, "Msg Date: " + msg.getCreationdate());
			if (Utils.stringToDate(msg.getCreationdate()).after(lowerLimit)) {
				Log.d(Constants.TAG, "Added");
				list.add(msg);
			}
		}
		return list.toArray(new Message[list.size()]);
	}
}
