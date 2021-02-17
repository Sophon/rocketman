package com.example.rocketman.launch.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rocketman.launch.Launch
import com.example.rocketman.launch.Repo
import kotlinx.coroutines.launch
import timber.log.Timber

private const val TAG = "LaunchDetail"
private const val MSG_EXCEPTION_LAUNCH_NULL = "cached launch shouldn't be null"

class LaunchDetailVM: ViewModel() {

    val launch = MutableLiveData<Launch>()
    private val repo = Repo.get()

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