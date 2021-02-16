package com.example.rocketman.event

import android.content.Context
import com.example.rocketman.common.BASE_URL_SPACEX
import com.example.rocketman.db.RocketManDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.IllegalStateException

private const val ERROR_MSG_NO_INSTANCE = "Events repository must be initialized!"

class Repo private constructor(context: Context) {

    private val api by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_SPACEX)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    private val dao: RocketManDB = RocketManDB.build(context)

    suspend fun getRemoteEvents() = api.getEvents()

    fun getLocalEvents() = dao.eventDao().getEvents()

    suspend fun updateLocalEvents() {
        val response = getRemoteEvents()

        if(response.isSuccessful) {
            response.body()?.let {
                withContext(Dispatchers.IO) {
                    dao.eventDao().saveEvents(it)
                }
            }
        }
    }

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