package com.codeninja.paymentservice.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {

	private String errorMessage;
	private String errorCode;
	private LocalDateTime timeStamp;
	private String path;
	
	
}
