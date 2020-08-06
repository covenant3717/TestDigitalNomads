package com.evgeny.testdigitalnomads.repository

import com.evgeny.testdigitalnomads.R
import com.evgeny.testdigitalnomads.model.Bid
import com.evgeny.testdigitalnomads.model.News
import com.evgeny.testdigitalnomads.repository.network.MainApiClient
import ru.vippolis.employeecontrol.repository.Resource
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

    suspend fun getNews(
        page: Int, onResult: (response: Resource<List<News>>) -> Unit
    ) = withContext(Dispatchers.IO) {
        if (isConnected()) {
            onResult(loading(true))

/*
            val response = mainApiClient.getNews(page = page)
            onResult(
                when (response) {
                    is Resource.Success -> Resource.Success(response.value.toBidList())
                    is Resource.Error -> response
                    is Resource.Progress -> response
                }
            )
*/

            onResult(loading(false))
        } else {
            onResult(Resource.Error(getStringRes(R.string.internet_disconnected)))
        }
    }

}