package com.mrsanglier.tsumegohero.localdatasources.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mrsanglier.tsumegohero.localdatasources.room.model.RoomTsumego
import kotlinx.coroutines.flow.Flow

@Dao
interface TsumegoDao {
    @Upsert
    suspend fun upsert(tsumegos: List<RoomTsumego>)

    @Query("SELECT * FROM tsumego")
    fun observeAllGames(): Flow<List<RoomTsumego>>

    @Query("SELECT * FROM tsumego WHERE id = :id")
    fun observeGame(id: String): Flow<RoomTsumego>

    @Query("DELETE FROM tsumego")
    suspend fun clean()
}
