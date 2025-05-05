package com.segittur.auditing;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * TestHelper is a utility class that provides helper methods for testing. 
 */
public class TestHelper {

    /**
     * Converts a string representation of a date and time to a Unix timestamp.
     *
     * @param stringFormat the string representation of the date and time in the format "yyyy-MM-dd HH:mm:ss"
     * @return the Unix timestamp corresponding to the provided date and time
     */
    public static long getDateTimeStampBy(String stringFormat) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime result = LocalDateTime.parse(stringFormat, dateTimeFormatter);
        return result.toEpochSecond(ZoneOffset.UTC);
    }
}
