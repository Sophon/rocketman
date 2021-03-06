package com.example.rocketman.features.company

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class CompanyVM(private val repo: CompanyRepo): ViewModel() {

    val companyData = MutableLiveData<Company>()

    init {
        getCompanyInfo()
        updateCompanyInfo()
    }

    fun updateCompanyInfo() {
        Timber.d("$TAG: updating")
        viewModelScope.launch {
            repo.updateLocalCompanyData()
            getCompanyInfo()
        }
    }

    private fun getCompanyInfo() {
        viewModelScope.launch {
            repo.getLocalCompanyData().collect {
                companyData.postValue(it)
            }
        }
    }
}

private const val TAG = "CompanyApi"
private const val MSG_SUCCESS = "successfully fetched"
private const val MSG_ERROR = "failed to fetch"