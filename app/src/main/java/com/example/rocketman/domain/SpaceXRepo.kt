package com.example.rocketman.domain

import android.content.Context
import com.example.rocketman.domain.networking.SpaceXApi
import kotlinx.coroutines.flow.Flow
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.lang.IllegalStateException

class SpaceXRepo private constructor(context: Context) {

    private val api: SpaceXApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/v4/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        api = retrofit.create(SpaceXApi::class.java)
    }

    companion object {
        private var INSTANCE: SpaceXRepo? = null

        fun init(context: Context) {
            if(INSTANCE == null) {
                INSTANCE = SpaceXRepo(context)
            }
        }

        fun get(): SpaceXRepo {
            return INSTANCE
                ?: throw IllegalStateException("Rocket repository must be initialized!")
        }
    }
}