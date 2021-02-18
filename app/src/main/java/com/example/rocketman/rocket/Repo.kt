package com.example.rocketman.rocket

import com.example.rocketman.db.RocketManDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

private const val ERROR_MSG_API = "failed to fetch remote rocket list"
private const val SUCCESS_MSG_API = "successfully fetched remote rocket list"
private const val TAG = "RocketRepo"

class Repo(
    private val api: RocketApi,
    private val dao: RocketManDB
) {

    private suspend fun getRemoteRockets() = api.getRockets()

    fun getAllLocalRockets() = dao.rocketDao().getRockets()

    fun getLocalRocket(rocketId: String) = dao.rocketDao().getRocket(rocketId)

    fun getActiveOnlyLocalRockets() = dao.rocketDao().getActiveRockets()

    suspend fun updateLocalRockets() {
        val response = getRemoteRockets()

        if(response.isSuccessful) {
            Timber.d("$TAG: $SUCCESS_MSG_API")
            response.body()?.let {
                withContext(Dispatchers.IO) {
                    dao.rocketDao().saveRockets(it)
                }
            }
        } else {
            Timber.d("$TAG: $ERROR_MSG_API")
        }
    }
}