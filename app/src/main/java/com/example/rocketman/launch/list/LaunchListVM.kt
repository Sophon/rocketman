package com.example.rocketman.launch.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rocketman.launch.Launch
import com.example.rocketman.launch.Repo
import kotlinx.coroutines.launch

class LaunchListVM: ViewModel() {

    val launches = MutableLiveData<List<Launch>>()
    private val repo = Repo.get()

    init {
        getLaunches()
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