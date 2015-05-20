package br.usinadigital.msgsystemwebapp.util;

/* map the file application.properties */
public class ApplicationConfig {

	private String webserviceURL;
	private String getAllCategories;
	private String sendMessageToUsersByCategories;

	public String getWebserviceURL() {
		return webserviceURL;
	}

	public void setWebserviceURL(String webserviceURL) {
		this.webserviceURL = webserviceURL;
	}

	public String getGetAllCategories() {
		return getAllCategories;
	}

	public void setGetAllCategories(String getAllCategories) {
		this.getAllCategories = getAllCategories;
	}

	public String getSendMessageToUsersByCategories() {
		return sendMessageToUsersByCategories;
	}

	public void setSendMessageToUsersByCategories(
			String sendMessageToUsersByCategories) {
		this.sendMessageToUsersByCategories = sendMessageToUsersByCategories;
	}
	
}
