package com.example.rocketman

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rocketman.features.company.Company
import com.example.rocketman.features.company.CompanyTypeConverter
import com.example.rocketman.features.company.CompanyDao
import com.example.rocketman.features.event.Event
import com.example.rocketman.features.event.EventDao
import com.example.rocketman.features.event.EventTypeConverter
import com.example.rocketman.features.rocket.PayloadWeight
import com.example.rocketman.features.rocket.Rocket
import com.example.rocketman.features.rocket.RocketDao
import com.example.rocketman.features.rocket.RocketTypeConverter

@Database(
    entities = [
        Company::class,
        Rocket::class,
        PayloadWeight::class,
        Event::class
    ],
    exportSchema = true,
    version = 1
)
@TypeConverters(
    CompanyTypeConverter::class,
    RocketTypeConverter::class,
    EventTypeConverter::class
)
abstract class RocketManDB: RoomDatabase() {

    abstract fun companyDao(): CompanyDao
    abstract fun rocketDao(): RocketDao
    abstract fun eventDao(): EventDao

    companion object {
        const val NAME_DB = "Rocket database"
    }
}