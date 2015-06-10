package br.usinadigital.msgsystemandroid.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import br.usinadigital.msgsystemandroid.MessageService;

public class Utils {

	public static boolean isNetworkConnected(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null) {
			return false;
		} else
			return true;
	}

	public static void initializeMessageService(Context context, long interval) {
		Intent notificationIntent = new Intent(context, MessageService.class);
		PendingIntent pendingIntent = PendingIntent.getService(context, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
		AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		am.setInexactRepeating(AlarmManager.RTC, System.currentTimeMillis(), interval, pendingIntent);
	}
	
	public static void reInitializeMessageService(Context context, long interval) {
		Intent notificationIntent = new Intent(context, MessageService.class);
		PendingIntent pendingIntent = PendingIntent.getService(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		am.setInexactRepeating(AlarmManager.RTC, System.currentTimeMillis(), interval, pendingIntent);
	}
	
	/*
	 * * Put the checked categories saved in android to the new categories list
	 * got from the WS
	 */
	public static Set<String> updateCategories(Map<String, String> newCat, Set<String> oldChecked) {
		Set<String> newChecked = new HashSet<String>();
		for (String id : oldChecked) {
			if (newCat.containsKey(id)) {
				newChecked.add(id);
			}
		}
		return newChecked;
	}

	public static Integer[] toStringArray(Set ids) {
		List<Integer> list = new ArrayList<Integer>();
		for (Object id : ids)
			list.add(Integer.valueOf(id.toString()));
		return list.toArray(new Integer[list.size()]);
	}

	public static List<Integer> getSortedkeys(Map map) {
		List<Integer> res = new ArrayList<Integer>();
		for (Object val : map.keySet()) {
			res.add(Integer.valueOf((String) val));
		}
		Collections.sort(res);
		return res;
	}

	public static String dateToString(Date date) {
		DateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		return formatter.format(date);
	}

	public static String dateToStringLocale(Date date) {
		return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(date);
	}

	public static Date stringToDate(String strDate) {
		DateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date = null;
		try {
			date = formatter.parse(strDate);

		} catch (ParseException e) {

			e.printStackTrace();
		}
		return date;
	}

}
