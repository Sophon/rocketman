package com.example.rocketman.event

import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("history")
    suspend fun getEvents(): Response<List<Event>>
}