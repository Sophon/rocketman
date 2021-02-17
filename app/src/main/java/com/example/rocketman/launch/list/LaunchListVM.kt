package com.example.rocketman.launch.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rocketman.launch.Launch
import com.example.rocketman.launch.Repo
import kotlinx.coroutines.launch
import retrofit2.Response

class LaunchListVM: ViewModel() {

    val launches = MutableLiveData<List<Launch>>()
    val launchStatus = MutableLiveData<LaunchFilter>()
    private val repo = Repo.get()

    init {
        getLaunches(repo::getRemoteAllLaunches)
    }

    fun filterLaunches(filter: LaunchFilter) {
        launchStatus.postValue(filter)

        getLaunches(
            when(filter) {
                LaunchFilter.ALL -> {
                    repo::getRemoteAllLaunches
                }
                LaunchFilter.PAST -> {
                    repo::getRemotePastLaunches
                }
                LaunchFilter.UPCOMING -> {
                    repo::getUpcomingPastLaunches
                }
            }
        )
    }

    fun updateLaunches() {
        getLaunches(repo::getRemoteAllLaunches)
    }

    private fun getLaunches(call: suspend () -> Response<List<Launch>>) {
        viewModelScope.launch {
            val response = call.invoke()

            if(response.isSuccessful) {
                response.body()?.let {
                    launches.postValue(it)
                }
            }
        }
    }
}