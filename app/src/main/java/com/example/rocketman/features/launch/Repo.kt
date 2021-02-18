package com.example.rocketman.features.launch

private const val ERROR_MSG_NO_INSTANCE = "Launch repository must be initialized!"

class Repo(
    private val api: Api
) {

    suspend fun getAllRemoteLaunches() = api.getAllLaunches()

    suspend fun getPastRemoteLaunches() = api.getPastLaunches()

    suspend fun getUpcomingRemoteLaunches() = api.getUpcomingLaunches()

    suspend fun getRemoteLaunch(id: String) = api.getLaunch(id)
}