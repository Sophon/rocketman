package com.example.rocketman.company

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

private const val ERROR_MSG_NO_INSTANCE = "Company repository must be initialized!"
private const val ERROR_MSG_API = "failed to fetch remote company info"
private const val SUCCESS_MSG_API = "successfully fetched remote company info"
private const val TAG = "CompanyRepo"

class Repo private constructor(context: Context) {

    private val api by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_SPACEX)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    private val dao: RocketManDB = RocketManDB.build(context)

    private suspend fun getRemoteCompanyData() = api.getCompanyInfo()

    fun getLocalCompanyData() = dao.companyDao().getCompanyData()

    suspend fun updateLocalCompanyData() {
        val response = getRemoteCompanyData()

        if(response.isSuccessful) {
            Timber.d("$TAG: $SUCCESS_MSG_API")
            response.body()?.let {
                withContext(Dispatchers.IO) {
                    dao.companyDao().saveCompanyData(it)
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