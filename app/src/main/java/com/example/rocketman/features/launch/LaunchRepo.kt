package com.example.rocketman.features.launch

class LaunchRepo(
    private val api: LaunchApi
) {

    suspend fun getAllRemoteLaunches() = api.getAllLaunches()

    suspend fun getPastRemoteLaunches() = api.getPastLaunches()

    suspend fun getUpcomingRemoteLaunches() = api.getUpcomingLaunches()
}