package com.malykhnik.freelanceexchnge.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateFormatter {
    public static String formatCurrentDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH-mm");
        return dateTime.format(formatter);
    }
}