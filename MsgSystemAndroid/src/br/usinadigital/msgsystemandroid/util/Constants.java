package br.usinadigital.msgsystemandroid.util;

public interface Constants {
	
	public static final int HTTP_TIMEOUT_MILLISEC = 5000;
	
	public static final String TAG = "br.usinadigital.msgsystemandroid";
	public static final String FILE_CATEGORY_NAME = "CategoryName";
	public static final String FILE_CATEGORY_CHECK = "CategoryCheck";
	public static final String FILE_MESSAGES = "Messages";
	public static final String FILE_CONFIGURATIONS = "Configurations";
	public static final String CONFIGURATION_LAST_UPDATE_CATEGORIES = "categoriesLastUpdate";
	public static final String CONFIGURATION_LAST_UPDATE_MESSAGES = "messagesLastDate";
	public static final String CONFIGURATION_HISTORY = "history";
	public static final String CONFIGURATION_FREQUENCY = "frequency";
	public static final String CONFIGURATION_FIRST_EXECUTION = "firstExecution";
	
	public static final int DEFAULT_CONFIGURATION_HISTORY = 0;
	public static final int DEFAULT_CONFIGURATION_FREQUENCY = 4;
	
	
	public static final String CHECKED_STATE = "1";
	public static final String UNCHECKED_STATE = "0";
	
	public static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";
}
