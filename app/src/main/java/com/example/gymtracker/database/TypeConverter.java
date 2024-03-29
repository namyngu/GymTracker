package com.example.gymtracker.database;

import java.util.Date;

public class TypeConverter {
    @androidx.room.TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @androidx.room.TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null? null : date.getTime();
    }
}

