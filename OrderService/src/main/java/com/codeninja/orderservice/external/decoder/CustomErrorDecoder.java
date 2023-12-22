package com.codeninja.orderservice.external.decoder;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.codeninja.orderservice.exception.CustomException;
import com.codeninja.orderservice.model.ErrorResponse;
import com.codeninja.orderservice.util.DateTimeUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		log.info("::{}", response.request().url());
		log.info("::{}", response.request().headers());
		try {
			byte[] responseBody = IOUtils.toByteArray(response.body().asInputStream());
            ErrorResponse errorResponse = objectMapper.readValue(responseBody, ErrorResponse.class);
			return new CustomException(errorResponse.getErrorMessage(), errorResponse.getErrorCode(), response.status(),
					DateTimeUtil.getZonedDateTime());
		} catch (IOException e) {
			throw new CustomException("Internal Server Error", "INTERNAL_SERVER_ERROR", 500, DateTimeUtil.getZonedDateTime());
		} 
	}

}
