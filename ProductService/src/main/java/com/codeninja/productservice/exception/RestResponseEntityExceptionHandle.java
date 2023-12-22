package com.codeninja.productservice.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.codeninja.productservice.model.ErrorResponse;

@ControllerAdvice
public class RestResponseEntityExceptionHandle extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ProductServiceCustomException.class)
	public ResponseEntity<ErrorResponse> productServiceException(ProductServiceCustomException exception) {
		return new ResponseEntity<>(ErrorResponse.builder().errorMessage(exception.getMessage())
				.errorCode(exception.getErrorCode()).timeStamp(Instant.now()).build(), HttpStatus.NOT_FOUND);
	}

}
