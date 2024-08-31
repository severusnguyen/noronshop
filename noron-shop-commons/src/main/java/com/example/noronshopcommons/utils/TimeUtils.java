package com.example.noronshopcommons.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TimeUtils {
    public static long getCurrentTimeLong() {
        return System.currentTimeMillis();
    }

    public static LocalDateTime longToLocalDateTime(Long time) {
        if (time == null) time = getCurrentTimeLong();
        return Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}
