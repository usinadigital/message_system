package br.usinadigital.msgsystemwebapp.util;

/* map the file application.properties */
public class ApplicationConfig {

	private String wsURL;
	private String wsGetAllCategories;
	private String wsSendMessageByCategories;
	
	public String getWsURL() {
		return wsURL;
	}
	public void setWsURL(String wsURL) {
		this.wsURL = wsURL;
	}
	public String getWsGetAllCategories() {
		return wsGetAllCategories;
	}
	public void setWsGetAllCategories(String wsGetAllCategories) {
		this.wsGetAllCategories = wsGetAllCategories;
	}
	public String getWsSendMessageByCategories() {
		return wsSendMessageByCategories;
	}
	public void setWsSendMessageByCategories(String wsSendMessageByCategories) {
		this.wsSendMessageByCategories = wsSendMessageByCategories;
	}
	
}
