package com.example.rocketman.features.rocket

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class RocketRepo(
    private val api: RocketApi,
    private val dao: RocketDao
) {

    private suspend fun getRemoteRockets() = api.getRockets()

    fun getAllLocalRockets() = dao.getRockets()

    fun getLocalRocket(rocketId: String) = dao.getRocket(rocketId)

    fun getActiveOnlyLocalRockets() = dao.getActiveRockets()

    suspend fun updateLocalRockets() {
        val response = getRemoteRockets()

        response?.let {
            if(response.isSuccessful) {
                Timber.d("$TAG: $SUCCESS_MSG_API")
                response.body()?.let {
                    withContext(Dispatchers.IO) {
                        dao.saveRockets(it)
                    }
                }
            } else {
                Timber.d("$TAG: $ERROR_MSG_API")
            }
        }
    }
}

private const val ERROR_MSG_API = "failed to fetch remote rocket list"
private const val SUCCESS_MSG_API = "successfully fetched remote rocket list"
private const val TAG = "RocketRepo"