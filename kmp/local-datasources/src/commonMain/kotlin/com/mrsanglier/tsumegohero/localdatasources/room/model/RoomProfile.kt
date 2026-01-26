package com.mrsanglier.tsumegohero.localdatasources.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class RoomProfile(
    @PrimaryKey val userId: String,
    val username: String,
)
