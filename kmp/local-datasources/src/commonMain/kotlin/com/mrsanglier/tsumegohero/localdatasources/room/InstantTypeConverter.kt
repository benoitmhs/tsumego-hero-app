package com.mrsanglier.tsumegohero.localdatasources.room

import androidx.room.TypeConverter
import kotlin.time.Instant

internal class InstantTypeConverter {

    @TypeConverter
    fun fromInstant(instant: Instant): Long {
        return instant.toEpochMilliseconds()
    }

    @TypeConverter
    fun toInstant(millis: Long): Instant {
        return Instant.fromEpochMilliseconds(millis)
    }
}