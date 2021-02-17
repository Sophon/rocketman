package com.example.rocketman.launch

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LaunchDao {

    @Query("SELECT * FROM launch")
    fun getLaunches(): Flow<List<Launch>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveLaunches(launches: List<Launch>)
}