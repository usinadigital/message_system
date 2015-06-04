package br.usinadigital.msgsystemwebapp.util;

/* map the file application.properties */
public class ApplicationConfig {

	private String wsURL;
	private String wsGetAllCategories;
	private String wsSaveMessage;
	
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
	public String getWsSaveMessage() {
		return wsSaveMessage;
	}
	public void setWsSaveMessage(String wsSaveMessage) {
		this.wsSaveMessage = wsSaveMessage;
	}
	
}
