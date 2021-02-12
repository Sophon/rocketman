package com.example.rocketman.rocket.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rocketman.rocket.Repo
import com.example.rocketman.rocket.Rocket
import kotlinx.coroutines.launch
import timber.log.Timber

private const val TAG = "RocketApi"
private const val MSG_SUCCESS = "successfully fetched"
private const val MSG_ERROR = "failed to fetch"

class RocketDetailVM: ViewModel() {

    val rocket = MutableLiveData<Rocket>()
}