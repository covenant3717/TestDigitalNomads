package com.evgeny.testdigitalnomads.repository

import com.evgeny.testdigitalnomads.repository.network.MainApiClient
import ru.vippolis.employeecontrol.repository.Resource
import com.evgeny.testdigitalnomads.repository.room.MainDao


class Repository constructor(
    private val mainApiClient: MainApiClient,
    private val mainDao: MainDao
) {

    private fun loading(load: Boolean) = Resource.Progress(load)

    //==============================================================================================

/*
    suspend fun getBids(
        request: GetBids, onResult: (response: Resource<List<Bid>>) -> Unit
    ) = withContext(Dispatchers.IO) {
        if (isConnected()) {
            onResult(loading(true))

            val response = mainApiClient.getBids(request)
            onResult(
                when (response) {
                    is Resource.Success -> Resource.Success(response.value.toBidList())
                    is Resource.Error -> response
                    is Resource.Progress -> response
                }
            )

            onResult(loading(false))
        } else {
            onResult(Resource.Error(getStringRes(R.string.internet_disconnected)))
        }
    }
*/

}