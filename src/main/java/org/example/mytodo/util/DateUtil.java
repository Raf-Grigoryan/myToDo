package org.example.mytodo.util;

import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final SimpleDateFormat TIME_STAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat WEB_DATE = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    public static String formatDate(Date date) {
        return TIME_STAMP_FORMAT.format(date);
    }

    @SneakyThrows
    public static Date parseDate(String date) {
        return TIME_STAMP_FORMAT.parse(date);
    }

    @SneakyThrows
    public static Date parseWebDate(String date) {
        return WEB_DATE.parse(date);
    }
}