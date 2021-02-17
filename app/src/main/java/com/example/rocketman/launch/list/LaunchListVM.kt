package com.example.rocketman.launch.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rocketman.launch.Launch
import com.example.rocketman.launch.Repo
import kotlinx.coroutines.launch

class LaunchListVM: ViewModel() {

    val launches = MutableLiveData<List<Launch>>()
    val launchStatus = MutableLiveData<LaunchFilter>()
    private val repo = Repo.get()

    init {
        getLaunches()
    }

    fun filterLaunches(filter: LaunchFilter) {
        launchStatus.postValue(filter)

        //TODO: database shit
    }

    fun updateLaunches() {
        //TODO: database shit
    }

    private fun getLaunches() {
        viewModelScope.launch {
            val response = repo.getRemoteRockets()

            if(response.isSuccessful) {
                response.body()?.let {
                    launches.postValue(it)
                }
            }
        }
    }
}