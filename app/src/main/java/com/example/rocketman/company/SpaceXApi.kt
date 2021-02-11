package com.example.rocketman.company

import com.example.rocketman.company.model.Company
import retrofit2.Response
import retrofit2.http.GET

interface SpaceXApi {

    @GET("company")
    suspend fun getCompanyInfo(): Response<Company>
}