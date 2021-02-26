package com.example.rocketman.features.rocket

import com.example.rocketman.RocketManDB
import com.example.rocketman.features.rocket.detail.RocketDetailVM
import com.example.rocketman.features.rocket.list.RocketListVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val rocketModule = module {

    fun provideRocketApi(retrofit: Retrofit): RocketApi {
        return retrofit.create(RocketApi::class.java)
    }

    single { provideRocketApi(get()) }
    single { get<RocketManDB>().rocketDao() }
    single { RocketRepo(get(), get()) }

    viewModel { RocketListVM(get()) }
    viewModel { RocketDetailVM(get()) }
}