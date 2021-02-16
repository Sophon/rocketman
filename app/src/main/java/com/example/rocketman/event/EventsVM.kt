package com.example.rocketman.event

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import timber.log.Timber

class EventsVM: ViewModel() {

    val events = MutableLiveData<List<Event>>()
    private val repo = Repo.get()

    init {
        getEvents()
    }

    fun getEvents() {
        viewModelScope.launch {
            repo.getRemoteEvents().body()?.let { eventList ->
                events.postValue(eventList.sortedByDescending { it.eventDateUnix })
            }
        }
    }

    fun sortAscending() {
        Timber.d("Sorting: ascending")
        events.value?.let { eventList ->
            events.postValue(eventList.sortedBy { it.eventDateUnix })
        }
    }

    fun sortDescending() {
        Timber.d("Sorting: descending")
        events.value?.let { eventList ->
            events.postValue(eventList.sortedByDescending { it.eventDateUnix })
        }
    }
}