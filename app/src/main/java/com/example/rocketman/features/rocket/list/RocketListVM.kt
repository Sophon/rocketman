package com.example.rocketman.features.rocket.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rocketman.features.rocket.RocketRepo
import com.example.rocketman.features.rocket.Rocket
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class RocketListVM(private val repo: RocketRepo): ViewModel() {

    val rockets = MutableLiveData<List<Rocket>>()
    var activeOnly = false

    init {
        toggleActiveOnly()
        updateRockets()
    }

    fun toggleActiveOnly(newActiveOnly: Boolean = false) {
        activeOnly = newActiveOnly

        getLocalRockets()
    }

    fun updateRockets() {
        Timber.d("$TAG: refreshing")
        viewModelScope.launch {
            repo.updateLocalRockets()
            getLocalRockets()
        }
    }

    private fun getLocalRockets() {
        viewModelScope.launch {
            if(activeOnly) {
                repo.getActiveOnlyLocalRockets().collect { rockets.postValue(it) }
            } else {
                repo.getAllLocalRockets().collect { rockets.postValue(it) }
            }
        }
    }
}

private const val TAG = "RocketApi"