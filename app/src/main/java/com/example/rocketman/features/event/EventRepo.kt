package com.example.rocketman.features.event

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EventRepo(
    private val api: EventApi,
    private val dao: EventDao
) {

    private suspend fun getRemoteEvents() = api.getEvents()

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

private const val ERROR_MSG_NO_INSTANCE = "Events repository must be initialized!"
