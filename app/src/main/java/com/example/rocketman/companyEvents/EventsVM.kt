package com.example.rocketman.companyEvents

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EventsVM: ViewModel() {

    val events = MutableLiveData<List<Event>>()
    private val repo = Repo.get()

    init {
        getEvents()
    }

    fun updateEvents() {
    }

    fun getEvents() {
        viewModelScope.launch {
            repo.getRemoteEvents().body()?.let {
                events.postValue(it)
            }
        }
    }
}