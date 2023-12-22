package com.codeninja.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.codeninja.orderservice.model.ErrorResponse;
import com.codeninja.orderservice.util.DateTimeUtil;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestResponseEntityExceptionHandle extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorResponse> customExceptionHandler(CustomException exception, HttpServletRequest request) {
		return new ResponseEntity<>(
				ErrorResponse.builder().errorCode("INTERNAL_SERVER_ERROR").errorMessage(exception.getMessage())
						.timeStamp(exception.getTimeStamp()).path(request.getRequestURI()).build(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> runtimeExceptionHandler(RuntimeException exception,
			HttpServletRequest request) {
		return new ResponseEntity<>(
				ErrorResponse.builder().errorCode("INTERNAL_SERVER_ERROR").errorMessage(exception.getMessage())
						.timeStamp(DateTimeUtil.getZonedDateTime()).path(request.getRequestURI()).build(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
