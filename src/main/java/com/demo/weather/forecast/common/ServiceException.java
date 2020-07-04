package com.demo.weather.forecast.common;

public class ServiceException extends Exception {
	private String errorCode;
	private String mesasge;
	public ServiceException(String errorCode, String mesasge) {
		this.errorCode = errorCode;
		this.mesasge = mesasge;
		
	}
	
	public String getErrorCode() {
		return this.errorCode;
	}

	public String getMessage() {
		return this.mesasge;
	}

}
