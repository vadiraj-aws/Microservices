package com.codeninja.paymentservice.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.codeninja.paymentservice.model.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestResponseEntityExceptionHandle extends ResponseEntityExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> runtimeExceptionHandler(RuntimeException exception,
			HttpServletRequest request) {
		return new ResponseEntity<>(
				ErrorResponse.builder().errorCode("INTERNAL_SERVER_ERROR").errorMessage(exception.getMessage())
						.timeStamp(LocalDateTime.now()).path(request.getRequestURI()).build(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
