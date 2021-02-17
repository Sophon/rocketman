package com.example.rocketman.rocket

import android.content.Context
import androidx.room.Room
import com.example.rocketman.common.BASE_URL_SPACEX
import com.example.rocketman.db.RocketManDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.lang.IllegalStateException

private const val ERROR_MSG_NO_INSTANCE = "Rocket repository must be initialized!"
private const val ERROR_MSG_API = "failed to fetch remote rocket list"
private const val SUCCESS_MSG_API = "successfully fetched remote rocket list"
private const val TAG = "RocketRepo"

class Repo private constructor(context: Context) {

    private val api by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_SPACEX)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    private val dao: RocketManDB = RocketManDB.build(context)

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