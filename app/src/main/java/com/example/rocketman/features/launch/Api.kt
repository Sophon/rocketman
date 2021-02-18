package com.example.rocketman.features.launch

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("launches")
    suspend fun getAllLaunches(): Response<List<Launch>>

    @GET("launches/past")
    suspend fun getPastLaunches(): Response<List<Launch>>

    @GET("launches/upcoming")
    suspend fun getUpcomingLaunches(): Response<List<Launch>>

    @GET("launches")
    suspend fun getLaunch(@Query("id") id: String): Response<Launch>
}