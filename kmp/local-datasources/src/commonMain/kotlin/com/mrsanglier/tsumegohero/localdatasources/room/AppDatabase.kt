package com.mrsanglier.tsumegohero.localdatasources.room

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import com.mrsanglier.tsumegohero.localdatasources.room.model.RoomProfile

@Database(
    entities = [
        RoomProfile::class,
    ],
    version = 1
)
@TypeConverters(InstantTypeConverter::class, DateTimeConverter::class)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}
