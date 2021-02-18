package com.example.rocketman.di

import androidx.room.Room
import com.example.rocketman.common.BASE_URL_SPACEX
import com.example.rocketman.features.company.companyModule
import com.example.rocketman.db.RocketManDB
import com.example.rocketman.features.event.eventModule
import com.example.rocketman.features.launch.launchModule
import com.example.rocketman.features.rocket.rocketModule
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_SPACEX)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { provideRetrofit() }
}

val persistenceModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            RocketManDB::class.java,
            RocketManDB.NAME_DB
        )
            .build()
    }
}

val allModules = listOf(
    networkModule,
    persistenceModule,
    companyModule,
    launchModule,
    eventModule,
    rocketModule
)