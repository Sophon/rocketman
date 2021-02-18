package com.example.rocketman.rocket

import com.example.rocketman.db.RocketManDB
import com.example.rocketman.rocket.detail.RocketDetailVM
import com.example.rocketman.rocket.list.RocketListVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val rocketModule = module {

    fun provideRocketApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    single { provideRocketApi(get()) }
    single { get<RocketManDB>().rocketDao() }
    single { Repo(get(), get()) }

    viewModel { RocketListVM(get()) }
    viewModel { RocketDetailVM(get()) }
}