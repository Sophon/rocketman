package com.example.rocketman.event

import com.example.rocketman.db.RocketManDB
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val eventModule = module {

    fun provideEventApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    single { provideEventApi(get()) }
    single { get<RocketManDB>().eventDao() }
    single { Repo(get(), get()) }

    viewModel { EventListVM(get()) }
}