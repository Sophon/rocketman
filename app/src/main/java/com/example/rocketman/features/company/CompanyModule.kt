package com.example.rocketman.features.company

import com.example.rocketman.RocketManDB
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val companyModule = module {

    fun provideCompanyApi(retrofit: Retrofit): CompanyApi {
        return retrofit.create(CompanyApi::class.java)
    }

    single { provideCompanyApi(get()) }
    single { get<RocketManDB>().companyDao() }
    single { CompanyRepo(get(), get()) }

    viewModel { CompanyVM(get()) }
}