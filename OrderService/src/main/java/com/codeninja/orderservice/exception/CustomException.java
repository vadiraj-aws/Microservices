package com.codeninja.orderservice.exception;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = -8879302250678686111L;
	
	private String errorCode;
	private int status;
	private ZonedDateTime timeStamp;

	public CustomException(String message, String errorCode, int status, ZonedDateTime date) {
		super(message);
		this.errorCode = errorCode;
		this.status = status;
		this.timeStamp = date;
	}

}
