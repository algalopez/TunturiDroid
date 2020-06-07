package com.algalopez.tunturi.droid.echo.repository

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.ZoneOffset.UTC

class DateTypeConverters {

    @TypeConverter
    fun epochToDate(value: Long?): LocalDateTime? {
        return value?.let { LocalDateTime.ofEpochSecond(it, 0, UTC) }
    }

    @TypeConverter
    fun dateToEpoch(date: LocalDateTime?): Long? {
        return date?.toEpochSecond(UTC)
    }
}
