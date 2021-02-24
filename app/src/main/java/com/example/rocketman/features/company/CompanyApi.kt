package com.example.rocketman.features.company

import retrofit2.Response
import retrofit2.http.GET

interface CompanyApi {

    @GET("company")
    suspend fun getCompanyInfo(): Response<Company>
}