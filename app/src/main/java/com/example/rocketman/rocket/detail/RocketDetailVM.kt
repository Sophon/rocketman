package com.example.rocketman.rocket.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rocketman.rocket.Repo
import com.example.rocketman.rocket.Rocket
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

private const val TAG = "RocketDetail"
private const val MSG_EXCEPTION_ROCKET_NULL = "cached rocket shouldn't be null"

class RocketDetailVM: ViewModel() {

    val rocket = MutableLiveData<Rocket>()
    private val repo = Repo.get()

    fun updateRocket() {
        viewModelScope.launch {
            Timber.d("$TAG: refreshing")
            repo.updateLocalRockets()

            rocket.value?.let { cachedRocket ->
                repo.getLocalRocket(cachedRocket.id).collect { newRocket ->
                    rocket.postValue(newRocket)
                }
            } ?: Timber.e("$TAG: $MSG_EXCEPTION_ROCKET_NULL")
        }
    }
}