package com.example.rocketman.features.company

import com.example.rocketman.db.RocketManDB
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val companyModule = module {

    fun provideCompanyApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    single { provideCompanyApi(get()) }
    single { get<RocketManDB>().companyDao() }
    single { Repo(get(), get()) }

    viewModel { CompanyVM(get()) }
}