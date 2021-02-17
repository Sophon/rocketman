package com.example.rocketman.launch

import android.content.Context
import com.example.rocketman.common.BASE_URL_SPACEX
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.IllegalStateException

private const val ERROR_MSG_NO_INSTANCE = "Launch repository must be initialized!"

class Repo private constructor(context: Context) {

    private val api by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_SPACEX)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    suspend fun getAllRemoteLaunches() = api.getAllLaunches()

    suspend fun getPastRemoteLaunches() = api.getPastLaunches()

    suspend fun getUpcomingRemoteLaunches() = api.getUpcomingLaunches()

    companion object {
        private var INSTANCE: Repo? = null

        fun init(context: Context) {
            if(INSTANCE == null) {
                INSTANCE = Repo(context)
            }
        }

        fun get(): Repo {
            return INSTANCE ?: throw IllegalStateException(ERROR_MSG_NO_INSTANCE)
        }
    }
}