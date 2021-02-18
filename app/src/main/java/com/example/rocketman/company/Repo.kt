package com.example.rocketman.company

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

private const val ERROR_MSG_API = "failed to fetch remote company info"
private const val SUCCESS_MSG_API = "successfully fetched remote company info"
private const val TAG = "CompanyRepo"

class Repo(
    private val dao: CompanyDao,
    private val api: Api
) {

    private suspend fun getRemoteCompanyData() = api.getCompanyInfo()

    fun getLocalCompanyData() = dao.getCompanyData()

    suspend fun updateLocalCompanyData() {
        val response = getRemoteCompanyData()

        if(response.isSuccessful) {
            Timber.d("$TAG: $SUCCESS_MSG_API")
            response.body()?.let {
                withContext(Dispatchers.IO) {
                    dao.saveCompanyData(it)
                }
            }
        } else {
            Timber.d("$TAG: $ERROR_MSG_API")
        }
    }
}