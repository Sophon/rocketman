package com.example.rocketman.rocket.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rocketman.rocket.Repo
import com.example.rocketman.rocket.Rocket
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

private const val TAG = "RocketApi"
private const val MSG_SUCCESS = "successfully fetched"
private const val MSG_ERROR = "failed to fetch"

class RocketListVM: ViewModel() {

    val rockets = MutableLiveData<List<Rocket>>()
    private val repo = Repo.get()

    init {
        getRockets()
        updateRockets()
    }

    private fun updateRockets() {
        viewModelScope.launch {
            repo.updateLocalRockets()
            getRockets()
        }
    }

    private fun getRockets() {
        viewModelScope.launch {
            repo.getLocalRockets().collect {
                rockets.postValue(it)
            }
        }
    }
}