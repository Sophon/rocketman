package com.example.rocketman

import android.app.Application
import timber.log.Timber

class RocketManApp: Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}