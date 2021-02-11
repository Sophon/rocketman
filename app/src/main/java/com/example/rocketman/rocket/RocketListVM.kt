package com.example.rocketman.rocket

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import timber.log.Timber

private const val TAG = "RocketApi"
private const val MSG_SUCCESS = "successfully fetched"
private const val MSG_ERROR = "failed to fetch"

class RocketListVM: ViewModel() {

    val rockets = MutableLiveData<List<Rocket>>()
    val repo = Repo.get()

    init {
        getRockets()
    }

    private fun getRockets() {
        viewModelScope.launch {
            val response = repo.getRockets()
            if(response.isSuccessful) {
                Timber.d("$TAG: $MSG_SUCCESS")
                response.body()?.let {
                    Timber.d("$TAG: ${it.size} rockets fetched")
                    rockets.postValue(it)
                }
            } else {
                Timber.e("$TAG: $MSG_ERROR")
            }
        }
    }
}