package com.example.rocketman.domain.networking

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SpaceXApi {

    @GET("/rockets")
    fun getRockets(): Call<SpaceXResponse>

    @GET("/rockets")
    fun getRocket(@Query("id") rocketId: String): Call<SpaceXResponse>
}