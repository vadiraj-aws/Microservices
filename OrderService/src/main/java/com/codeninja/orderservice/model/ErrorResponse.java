package com.codeninja.orderservice.model;

import java.time.ZonedDateTime;

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
	private ZonedDateTime timeStamp;
	private String path;

}
