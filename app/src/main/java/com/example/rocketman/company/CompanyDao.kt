package com.example.rocketman.company

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CompanyDao {

    @Query("SELECT * FROM company LIMIT 1")
    fun getCompanyData(): Flow<Company>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCompanyData(company: Company)
}