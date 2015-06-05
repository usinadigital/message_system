package br.usinadigital.msgsystemws.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

public class Utils {
	
	public static String intArrayToString(int[] intArray){
		String strArray = Arrays.toString(intArray);
        return strArray.substring(1,strArray.length()-1);
	}
	
	public static int sumIntArray(int[] array){
		int sum = 0;
		for(int item : array) sum += item;
		
		return sum;
	}
	
	public static String fromDateToString(Date date){
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT);
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
		return df.format(date);
	}
	
	public static Date fromStringToDate(String date) throws ParseException{
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT);
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
		return df.parse(date);
	}
	
}
