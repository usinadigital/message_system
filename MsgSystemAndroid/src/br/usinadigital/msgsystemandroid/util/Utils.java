package br.usinadigital.msgsystemandroid.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Utils {

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

	public static String dateToString(Date date) {
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT);
		return df.format(date);
	}

	public static Date stringToDate(String strDate) {
		DateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
		Date date = null;
		try {
			date = formatter.parse(strDate);
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return date;
	}

}
