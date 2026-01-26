package com.mrsanglier.tsumegohero.localdatasources.room

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

class DateTimeConverter {
    @TypeConverter
    fun fromLocalDate(date: LocalDate?): String? {
        return date?.toString() // ISO-8601 format: yyyy-MM-dd
    }

    @TypeConverter
    fun toLocalDate(dateString: String?): LocalDate? {
        return dateString?.let { LocalDate.parse(it) }
    }

    // LocalTime to String
    @TypeConverter
    fun fromLocalTime(time: LocalTime?): String? {
        return time?.toString() // ISO-8601: HH:mm:ss
    }

    @TypeConverter
    fun toLocalTime(timeString: String?): LocalTime? {
        return timeString?.let { LocalTime.parse(it) }
    }
}