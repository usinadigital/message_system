package br.usinadigital.msgsystemws.util;

public class Constants {

	public static final String GET_TEST_CATEGORY = "/rest/category/test";
	public static final String GET_CATEGORY = "/rest/category/{id}";
	public static final String GET_ALL_CATEGORY = "/rest/category/all";
	public static final String CREATE_CATEGORY = "/rest/category/create";
	public static final String DELETE_CATEGORY = "/rest/category/delete/{id}";
	
	public static final String GET_TEST_MESSAGE = "/rest/message/test";
	public static final String GET_ALL_MESSAGE = "/rest/message/all";
	public static final String SAVE_MESSAGE = "/rest/message/save";
	public static final String SEND_MESSAGE_BY_CATEGORIES = "/rest/message/sendByCategories";
	
	public static final String TABLE_MESSAGE = "messages";
	public static final String TABLE_FIELD_ID = "id";
	public static final String TABLE_MESSAGE_FIELD_TEXT = "text";
	public static final String TABLE_MESSAGE_FIELD_CREATIONDATE = "creationdate";
	
	public static final String TABLE_FIELD_LASTUPDATE = "lastupdate";

	public static final String TABLE_CATEGORY = "categories";
	public static final String TABLE_CATEGORY_FIELD_ID = "id";
	public static final String TABLE_CATEGORY_FIELD_NAME = "name";
	
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static final String GENERIC_SERVICE_ERROR = "E000";
	public static final int HTTP_INTERNAL_SERVER_ERROR = 500;
	
}
