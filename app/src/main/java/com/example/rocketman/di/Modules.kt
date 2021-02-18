package com.example.rocketman.di

import androidx.room.Room
import com.example.rocketman.common.BASE_URL_SPACEX
import com.example.rocketman.company.companyModule
import com.example.rocketman.db.RocketManDB
import com.example.rocketman.event.eventModule
import com.example.rocketman.rocket.Repo
import com.example.rocketman.rocket.Api
import com.example.rocketman.rocket.detail.RocketDetailVM
import com.example.rocketman.rocket.list.RocketListVM
import com.example.rocketman.rocket.rocketModule
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
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
    eventModule,
    rocketModule
)