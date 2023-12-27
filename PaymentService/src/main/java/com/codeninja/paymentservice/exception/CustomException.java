package com.codeninja.paymentservice.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException{
	
	private static final long serialVersionUID = -3045728852455054496L;
	private String errorCode;

	public CustomException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

}
