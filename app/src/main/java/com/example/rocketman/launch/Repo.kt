package com.example.rocketman.launch

import android.content.Context
import com.example.rocketman.common.BASE_URL_SPACEX
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.IllegalStateException

private const val ERROR_MSG_NO_INSTANCE = "Launch repository must be initialized!"

class Repo(
    private val api: Api
) {

    suspend fun getAllRemoteLaunches() = api.getAllLaunches()

    suspend fun getPastRemoteLaunches() = api.getPastLaunches()

    suspend fun getUpcomingRemoteLaunches() = api.getUpcomingLaunches()

    suspend fun getRemoteLaunch(id: String) = api.getLaunch(id)
}