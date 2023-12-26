package com.codeninja.orderservice;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Test {
	
	public static void main(String[] args) {
        // Explicitly specify the timezone as "Asia/Kolkata"
        ZoneId indiaTimeZone = ZoneId.of("Asia/Kolkata");
        
        Instant instant = Instant.now();
        ZonedDateTime zonedDateTime = instant.atZone(indiaTimeZone);
        
        System.out.println("Current time in India: " + zonedDateTime);
        
        System.out.println("----"+ LocalDateTime.now());
    }

}
