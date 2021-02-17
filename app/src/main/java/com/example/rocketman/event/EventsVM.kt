package com.example.rocketman.event

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import timber.log.Timber

class EventsVM: ViewModel() {

    val events = MutableLiveData<List<Event>>()
    var sorting = Sorting.DESCENDING
    private val repo = Repo.get()

    init {
        getEvents()
        updateEvents()
    }

    fun updateSorting(newSorting: Sorting) {
        sorting = newSorting
        if(newSorting == Sorting.ASCENDING) {
            sortAscending()
        } else {
            sortDescending()
        }
    }

    private fun getEvents() {
        viewModelScope.launch {
            repo.getRemoteEvents().body()?.let { eventList ->
                events.postValue(
                    if(sorting == Sorting.ASCENDING) eventList.sortedBy { it.eventDateUnix }
                    else eventList.sortedByDescending { it.eventDateUnix }
                )
            }
        }
    }

    fun updateEvents() {
        viewModelScope.launch {
            repo.updateLocalEvents()
            getEvents()
        }
    }

    private fun sortAscending() {
        Timber.d("Sorting: ascending")
        events.value?.let { eventList ->
            events.postValue(eventList.sortedBy { it.eventDateUnix })
        }
    }

    private fun sortDescending() {
        Timber.d("Sorting: descending")
        events.value?.let { eventList ->
            events.postValue(eventList.sortedByDescending { it.eventDateUnix })
        }
    }
}