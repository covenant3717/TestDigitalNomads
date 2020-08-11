package com.evgeny.testdigitalnomads.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.evgeny.testdigitalnomads.model.DBNews
import com.evgeny.testdigitalnomads.repository.network.MainApi
import com.evgeny.testdigitalnomads.repository.network.MainApiClient
import com.evgeny.testdigitalnomads.repository.network.NewsPagingSource
import com.evgeny.testdigitalnomads.repository.room.MainDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext


class Repository constructor(
    private val mainApiClient: MainApiClient,
    private val mainDao: MainDao,
    private val mainApi: MainApi
) {

    private fun loading(load: Boolean) = Resource.Progress(load)

    //==============================================================================================

    private suspend fun saveNews(listNetNews: List<DBNews>) = withContext(Dispatchers.IO) {
        mainDao.insert(listNetNews)
    }

    suspend fun clearCache() = withContext(Dispatchers.IO) {
        mainDao.clearNews()
    }

    suspend fun getNewsFlow(): Flow<PagingData<DBNews>> {
        return Pager(PagingConfig(pageSize = 5, prefetchDistance = 5)) {
            NewsPagingSource(mainApi)
        }.flow
    }

}