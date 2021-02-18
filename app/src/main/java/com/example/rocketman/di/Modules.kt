package com.example.rocketman.di

import androidx.room.Room
import com.example.rocketman.common.BASE_URL_SPACEX
import com.example.rocketman.db.RocketManDB
import com.example.rocketman.rocket.Repo
import com.example.rocketman.rocket.RocketApi
import com.example.rocketman.rocket.detail.RocketDetailVM
import com.example.rocketman.rocket.list.RocketListVM
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

    fun provideRocketApi(retrofit: Retrofit): RocketApi {
        return retrofit.create(RocketApi::class.java)
    }

    single { provideRetrofit() }
    single { provideRocketApi(get()) }
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

    single { get<RocketManDB>().rocketDao() }
}

val repoModule = module {

    single {
        Repo(get(), get())
    }
}

val viewModelModule = module {
    viewModel { RocketListVM(get()) }
    viewModel { RocketDetailVM(get()) }
}

val allModules = listOf(
    networkModule,
    persistenceModule,
    repoModule,
    viewModelModule
)