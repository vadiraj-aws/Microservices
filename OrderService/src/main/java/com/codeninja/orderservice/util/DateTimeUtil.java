package com.codeninja.orderservice.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DateTimeUtil {

	private static String timezone; // Make it static

	@Value("${spring.timezone}")
	public void setTimezone(String timezone) {
		DateTimeUtil.timezone = timezone;
	}

	public static ZonedDateTime getZonedDateTime() {
		return ZonedDateTime.ofInstant(Instant.now(), ZoneId.of(timezone));
	}

}
