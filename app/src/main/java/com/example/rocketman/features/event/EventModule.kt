package com.example.rocketman.features.event

import com.example.rocketman.RocketManDB
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val eventModule = module {

    fun provideEventApi(retrofit: Retrofit): EventApi {
        return retrofit.create(EventApi::class.java)
    }

    single { provideEventApi(get()) }
    single { get<RocketManDB>().eventDao() }
    single { EventRepo(get(), get()) }

    viewModel { EventListVM(get()) }
}