package com.example.rocketman.company

import android.content.Context
import com.example.rocketman.common.BASE_URL_SPACEX
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.IllegalStateException

private const val ERROR_MSG_NO_INSTANCE = "Company repository must be initialized!"

class Repo private constructor(context: Context) {

    private val api by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_SPACEX)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    suspend fun getCompanyData() = api.getCompanyInfo()

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