package com.evgeny.testdigitalnomads.repository.network

import com.evgeny.testdigitalnomads.base.BaseApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MainApiClient(private val mainApi: MainApi) : BaseApiClient() {

/*
    suspend fun getNews(page: Int) = withContext(Dispatchers.IO) {
        safeApiCall { mainApi.getNews(page = page) }
    }
*/


}