package com.example.rocketman.features.launch

class Repo(
    private val api: Api
) {

    suspend fun getAllRemoteLaunches() = api.getAllLaunches()

    suspend fun getPastRemoteLaunches() = api.getPastLaunches()

    suspend fun getUpcomingRemoteLaunches() = api.getUpcomingLaunches()
}