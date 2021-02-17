package com.example.rocketman.launch

import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("launches")
    suspend fun getAllLaunches(): Response<List<Launch>>

    @GET("launches/past")
    suspend fun getPastLaunches(): Response<List<Launch>>

    @GET("launches/upcoming")
    suspend fun getUpcomingLaunches(): Response<List<Launch>>
}