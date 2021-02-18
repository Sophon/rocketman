package com.example.rocketman.launch

import com.example.rocketman.launch.detail.LaunchDetailVM
import com.example.rocketman.launch.list.LaunchListVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val launchModule = module {

    fun provideLaunchApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    single { provideLaunchApi(get()) }
    single { Repo(get()) }

    viewModel { LaunchListVM(get()) }
    viewModel { LaunchDetailVM(get()) }
}