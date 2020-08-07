package com.evgeny.testdigitalnomads.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.evgeny.testdigitalnomads.R
import com.evgeny.testdigitalnomads.model.DBNews
import com.evgeny.testdigitalnomads.model.News
import com.evgeny.testdigitalnomads.repository.network.MainApiClient
import com.evgeny.testdigitalnomads.repository.room.AppDatabase
import com.evgeny.testdigitalnomads.repository.room.MainDao
import com.evgeny.testdigitalnomads.util.NewsBoundaryCallback
import com.evgeny.testdigitalnomads.util.getStringRes
import com.evgeny.testdigitalnomads.util.isConnected
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.vippolis.employeecontrol.repository.Resource


class Repository constructor(
    private val mainApiClient: MainApiClient,
    private val mainDao: MainDao
) {

    private fun loading(load: Boolean) = Resource.Progress(load)

    //==============================================================================================

    suspend fun getNews(
        pageSize: Int, onResult: (response: Resource<List<News>>) -> Unit
    ) = withContext(Dispatchers.IO) {
        if (isConnected()) {
            onResult(loading(true))

            val response = mainApiClient.getNews(pageSize = pageSize)
//            onResult(
                when (response) {
                    is Resource.Success -> {
                        mainDao.insert(response.value.toListDBNews())
                    }
                    is Resource.Error -> response
                    is Resource.Progress -> response
                }
//            )

            onResult(loading(false))
        } else {
            onResult(Resource.Error(getStringRes(R.string.internet_disconnected)))
        }
    }

    suspend fun getPagedNewsList(): LiveData<PagedList<DBNews>> = withContext(Dispatchers.IO) {
        val pagedListBuilder: LivePagedListBuilder<Int, DBNews> =
            LivePagedListBuilder<Int, DBNews>(mainDao.getNews(), 5)

        pagedListBuilder.setBoundaryCallback(NewsBoundaryCallback())

        return@withContext pagedListBuilder.build()
    }

}