package com.example.rocketman.company

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    }

    private fun getCompanyInfo() {
        viewModelScope.launch {
            val response = repo.getCompanyData()

            if(response.isSuccessful) {
                Timber.d("$TAG: $MSG_SUCCESS")
                response.body()?.let {
                    Timber.d("$TAG: $it")
                    companyData.postValue(it)
                }
            } else {
                Timber.e("$TAG: $MSG_ERROR")
            }
        }
    }
}