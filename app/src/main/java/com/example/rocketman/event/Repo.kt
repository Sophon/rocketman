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

class Repo(
    private val api: Api,
    private val dao: EventDao
) {

    suspend fun getRemoteEvents() = api.getEvents()

    fun getLocalEvents() = dao.getEvents()

    suspend fun updateLocalEvents() {
        val response = getRemoteEvents()

        if(response.isSuccessful) {
            response.body()?.let {
                withContext(Dispatchers.IO) {
                    dao.saveEvents(it)
                }
            }
        }
    }
}