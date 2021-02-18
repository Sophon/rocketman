package com.example.rocketman.event

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class EventListVM(private val repo: Repo): ViewModel() {

    val events = MutableLiveData<List<Event>>()
    var sorting = Sorting.DESCENDING

    init {
        updateSorting()
        updateEvents()
    }

    fun updateSorting(newSorting: Sorting = Sorting.DESCENDING) {
        sorting = newSorting

        getSortedLocalEvents()
    }

    fun updateEvents() {
        viewModelScope.launch {
            repo.updateLocalEvents()
            getSortedLocalEvents()
        }
    }

    private fun getSortedLocalEvents() {
        viewModelScope.launch {
            repo.getLocalEvents().collect { eventList ->
                if(sorting == Sorting.ASCENDING) {
                    events.postValue(eventList.sortedBy { it.eventDateUnix })
                } else {
                    events.postValue(eventList.sortedByDescending { it.eventDateUnix })
                }
            }
        }
    }
}