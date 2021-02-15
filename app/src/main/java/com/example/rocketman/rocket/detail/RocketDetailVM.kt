package com.example.rocketman.rocket.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rocketman.rocket.Repo
import com.example.rocketman.rocket.Rocket
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RocketDetailVM: ViewModel() {

    val rocket = MutableLiveData<Rocket>()
    private val repo = Repo.get()

    fun updateRocket(rocketId: String) {
        viewModelScope.launch {
            repo.updateLocalRockets()
            repo.getLocalRocket(rocketId).collect {
                rocket.postValue(it)
            }
        }
    }
}