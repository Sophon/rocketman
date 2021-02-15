package com.example.rocketman.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rocketman.company.Company
import com.example.rocketman.company.CompanyTypeConverter
import com.example.rocketman.company.Dao
import com.example.rocketman.rocket.Rocket
import com.example.rocketman.rocket.RocketTypeConverter

@Database(
    entities = [
        Company::class,
        Rocket::class
    ],
    exportSchema = true,
    version = 1
)
@TypeConverters(
    CompanyTypeConverter::class,
    RocketTypeConverter::class
)
abstract class Database: RoomDatabase() {

    abstract fun companyDao(): Dao

    companion object {
        const val NAME_DB = "Rocket database"
    }
}