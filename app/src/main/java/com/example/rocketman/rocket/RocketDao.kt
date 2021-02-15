package com.example.rocketman.rocket

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RocketDao {

    @Query("SELECT * FROM rocket")
    fun getRockets(): Flow<List<Rocket>>

    @Query("SELECT * FROM rocket WHERE id = :id")
    fun getRocket(id: String): Flow<Rocket>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveRockets(rockets: List<Rocket>)
}