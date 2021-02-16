package com.example.rocketman.launch

import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("launches")
    suspend fun getLaunches(): Response<List<Launch>>
}