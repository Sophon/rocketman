package com.example.rocketman.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rocketman.company.Company
import com.example.rocketman.company.CompanyTypeConverter
import com.example.rocketman.company.CompanyDao
import com.example.rocketman.event.Event
import com.example.rocketman.event.EventDao
import com.example.rocketman.event.EventTypeConverter
import com.example.rocketman.rocket.Rocket
import com.example.rocketman.rocket.RocketDao
import com.example.rocketman.rocket.RocketTypeConverter

@Database(
    entities = [
        Company::class,
        Rocket::class,
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

        fun build(context: Context): RocketManDB {
            return Room.databaseBuilder(
                context,
                RocketManDB::class.java,
                NAME_DB
            )
                .build()
        }
    }
}