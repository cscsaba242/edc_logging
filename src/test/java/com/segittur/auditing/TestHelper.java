package com.segittur.auditing;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class TestHelper {

    public static long getDateTimeStampBy(String stringFormat) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime result = LocalDateTime.parse(stringFormat, dateTimeFormatter);
        return result.toEpochSecond(ZoneOffset.UTC);
    }
}
