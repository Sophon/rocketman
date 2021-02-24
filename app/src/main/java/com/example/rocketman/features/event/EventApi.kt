package com.example.rocketman.features.event

import retrofit2.Response
import retrofit2.http.GET

interface EventApi {

    @GET("history")
    suspend fun getEvents(): Response<List<Event>>
}