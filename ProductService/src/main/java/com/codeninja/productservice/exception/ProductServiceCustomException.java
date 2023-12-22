package com.codeninja.productservice.exception;

import lombok.Data;

@Data
public class ProductServiceCustomException extends RuntimeException {

	private static final long serialVersionUID = -3045728852455054496L;
	private String errorCode;

	public ProductServiceCustomException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

}
