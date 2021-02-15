package com.example.rocketman.companyEvents

import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("history")
    suspend fun getEvents(): Response<List<Event>>
}