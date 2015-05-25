package br.usinadigital.msgsystemws.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorMessage {
	
	private static final Logger logger = LoggerFactory.getLogger(ErrorMessage.class);
	
	private int httpStatus;
	private String errorCode;
	private String userMessage;
	private String developerMessage;
	private String otherInfo;
	
	public ErrorMessage(int httpStatus, String errorCode, String userMessage,
			String developerMessage) {
		super();
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.userMessage = userMessage;
		this.developerMessage = developerMessage;
	}
	
	public ErrorMessage(int httpStatus, String errorCode, String userMessage,
			String developerMessage, String otherInfo) {
		super();
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.userMessage = userMessage;
		this.developerMessage = developerMessage;
		this.otherInfo = otherInfo;
	}

	public int getHttpStatus() {
		return httpStatus;
	}
	
	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getUserMessage() {
		return userMessage;
	}
	
	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}
	
	public String getDeveloperMessage() {
		return developerMessage;
	}
	
	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
	
}
