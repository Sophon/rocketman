package com.example.rocketman.company

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Query("SELECT * FROM company LIMIT 1")
    abstract fun getCompanyData(): Flow<Company>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveCompanyData(company: Company)
}