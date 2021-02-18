package com.example.rocketman.features.rocket

import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("rockets")
    suspend fun getRockets(): Response<List<Rocket>>
}