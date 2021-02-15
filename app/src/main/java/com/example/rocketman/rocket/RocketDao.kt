package com.example.rocketman.rocket

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RocketDao {

    @Query("SELECT * FROM rocket")
    fun getRockets(): Flow<List<Rocket>>

    @Query("SELECT * FROM rocket WHERE id = :id")
    fun getRocket(id: String): Flow<Rocket>
}