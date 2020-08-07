package com.evgeny.testdigitalnomads.repository

import com.evgeny.testdigitalnomads.R
import com.evgeny.testdigitalnomads.model.News
import com.evgeny.testdigitalnomads.repository.network.MainApiClient
import com.evgeny.testdigitalnomads.util.getStringRes
import com.evgeny.testdigitalnomads.util.isConnected
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.vippolis.employeecontrol.repository.Resource


class Repository constructor(
    private val mainApiClient: MainApiClient
//    private val mainDao: MainDao
) {

    private fun loading(load: Boolean) = Resource.Progress(load)

    //==============================================================================================

    suspend fun getNews(
        page: Int, pageSize: Int, onResult: (response: Resource<List<News>>) -> Unit
    ) = withContext(Dispatchers.IO) {
        if (isConnected()) {
            onResult(loading(true))

            val response = mainApiClient.getNews(page = page, pageSize = pageSize)
            onResult(
                when (response) {
                    is Resource.Success -> Resource.Success(response.value.toListNews())
                    is Resource.Error -> response
                    is Resource.Progress -> response
                }
            )

            onResult(loading(false))
        } else {
            onResult(Resource.Error(getStringRes(R.string.internet_disconnected)))
        }
    }

}