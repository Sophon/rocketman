package com.example.rocketman.company

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

private const val TAG = "CompanyApi"
private const val MSG_SUCCESS = "successfully fetched"
private const val MSG_ERROR = "failed to fetch"

class CompanyVM: ViewModel() {

    val companyData = MutableLiveData<Company>()
    private val repo = Repo.get()

    init {
        getCompanyInfo()
        updateCompanyInfo()
    }

    private fun updateCompanyInfo() {
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