package com.example.rocketman.features.launch.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rocketman.features.launch.Launch
import com.example.rocketman.features.launch.Repo
import kotlinx.coroutines.launch
import timber.log.Timber

private const val TAG = "LaunchDetail"
private const val MSG_EXCEPTION_LAUNCH_NULL = "cached launch shouldn't be null"

class LaunchDetailVM(private val repo: Repo): ViewModel() {

    val launch = MutableLiveData<Launch>()

    fun updateLaunch() {
        Timber.d("$TAG: refreshing")

        launch.value?.let {
            viewModelScope.launch {
                val response = repo.getRemoteLaunch(it.id)

                if(response.isSuccessful) {
                    response.body()?.let {
                        launch.postValue(it)
                    }
                }
            }
        } ?: Timber.e("$TAG: $MSG_EXCEPTION_LAUNCH_NULL")
    }
}