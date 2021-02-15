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

    @Query("SELECT * FROM rocket WHERE id IS :id LIMIT 1")
    fun getRocket(id: String): Flow<Rocket>

    @Query("SELECT * FROM rocket WHERE active IS 1")
    fun getActiveRockets(): Flow<List<Rocket>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveRockets(rockets: List<Rocket>)
}