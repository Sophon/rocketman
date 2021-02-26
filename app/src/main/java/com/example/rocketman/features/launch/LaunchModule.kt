package com.example.rocketman.features.launch

import com.example.rocketman.features.launch.list.LaunchListVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val launchModule = module {

    fun provideLaunchApi(retrofit: Retrofit): LaunchApi {
        return retrofit.create(LaunchApi::class.java)
    }

    single { provideLaunchApi(get()) }
    single { LaunchRepo(get()) }

    viewModel { LaunchListVM(get()) }
}