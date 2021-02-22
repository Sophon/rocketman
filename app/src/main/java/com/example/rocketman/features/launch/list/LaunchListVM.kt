package com.example.rocketman.features.launch.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rocketman.features.launch.Launch
import com.example.rocketman.features.launch.Repo
import kotlinx.coroutines.launch
import timber.log.Timber

class LaunchListVM(private val repo: Repo): ViewModel() {

    val launches = MutableLiveData<List<Launch>>()
    var filter = LaunchFilter.ALL

    fun filterLaunches(newFilter: LaunchFilter = LaunchFilter.ALL) {
        filter = newFilter

        getRemoteLaunches()
    }

    fun updateLaunches() {
        Timber.d("$TAG: refreshing")
        getRemoteLaunches()
    }

    private fun getRemoteLaunches() {
        viewModelScope.launch {
            val response = when(filter) {
                LaunchFilter.PAST -> {
                    repo.getPastRemoteLaunches()
                }
                LaunchFilter.UPCOMING -> {
                    repo.getUpcomingRemoteLaunches()
                }
                else -> {
                    repo.getAllRemoteLaunches()
                }
            }

            if(response.isSuccessful) {
                response.body()?.let {
                    launches.postValue(it)
                }
            }
        }
    }
}

private const val TAG = "LaunchList"