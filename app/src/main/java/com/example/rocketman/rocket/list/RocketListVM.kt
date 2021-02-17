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

class RocketListVM: ViewModel() {

    val rockets = MutableLiveData<List<Rocket>>()
    var activeOnly = false
    private val repo = Repo.get()

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