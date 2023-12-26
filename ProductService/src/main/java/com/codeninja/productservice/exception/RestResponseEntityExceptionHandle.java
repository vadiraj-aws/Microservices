package com.codeninja.productservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.codeninja.productservice.model.ErrorResponse;
import com.codeninja.productservice.util.DateTimeUtil;

@ControllerAdvice
public class RestResponseEntityExceptionHandle extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ProductServiceCustomException.class)
	public ResponseEntity<ErrorResponse> productServiceException(ProductServiceCustomException exception) {
		return new ResponseEntity<>(ErrorResponse.builder().errorMessage(exception.getMessage())
				.errorCode(exception.getErrorCode()).timeStamp(DateTimeUtil.getZonedDateTime()).build(), HttpStatus.NOT_FOUND);
	}
	
}
