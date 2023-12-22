package com.codeninja.orderservice.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.codeninja.orderservice.model.ErrorResponse;

@ControllerAdvice
public class RestResponseEntityExceptionHandle extends ResponseEntityExceptionHandler{

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> runtimeExceptionHandler(RuntimeException exception) {
		return new ResponseEntity<>(ErrorResponse.builder().errorCode("INTERNAL_SERVER_ERROR")
				.errorMessage(exception.getMessage()).timeStamp(Instant.now()).build(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
