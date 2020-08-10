package com.evgeny.testdigitalnomads.repository

import androidx.paging.DataSource
import com.evgeny.testdigitalnomads.R
import com.evgeny.testdigitalnomads.model.DBNews
import com.evgeny.testdigitalnomads.repository.network.MainApiClient
import com.evgeny.testdigitalnomads.repository.room.MainDao
import com.evgeny.testdigitalnomads.util.getStringRes
import com.evgeny.testdigitalnomads.util.isConnected
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class Repository constructor(
    private val mainApiClient: MainApiClient,
    private val mainDao: MainDao
) {

    private fun loading(load: Boolean) = Resource.Progress(load)

    //==============================================================================================

    private suspend fun saveNews(listNetNews: List<DBNews>) = withContext(Dispatchers.IO) {
        mainDao.insert(listNetNews)
    }

    suspend fun clearCache() = withContext(Dispatchers.IO) {
        mainDao.clearNews()
    }

    fun getNewsDataSource(): DataSource.Factory<Int, DBNews> {
        return mainDao.getNews()
    }

    suspend fun getNews(
        page: Int, onResult: (response: Resource<String>) -> Unit
    ) = withContext(Dispatchers.IO) {
        if (isConnected()) {
            onResult(loading(true))

            val response = mainApiClient.getNews(page = page)
            onResult(
                when (response) {
                    is Resource.Success -> {
                        saveNews(response.value.toListDBNews())
                        Resource.Success("success")
                    }
                    is Resource.Error -> response
                    is Resource.Progress -> response
                }
            )

            onResult(loading(false))
        } else onResult(Resource.Error(getStringRes(R.string.internet_disconnected)))

    }

    suspend fun getNews2(
        page: Int, onResult: (response: Resource<List<DBNews>>) -> Unit
    ) = withContext(Dispatchers.IO) {
        if (isConnected()) {
            onResult(loading(true))

            val response = mainApiClient.getNews(page = page)
            onResult(
                when (response) {
                    is Resource.Success -> Resource.Success(response.value.toListDBNews())
                    is Resource.Error -> response
                    is Resource.Progress -> response
                }
            )

            onResult(loading(false))
        } else onResult(Resource.Error(getStringRes(R.string.internet_disconnected)))

    }


}