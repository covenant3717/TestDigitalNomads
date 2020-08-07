package com.evgeny.testdigitalnomads.repository.network

import com.evgeny.testdigitalnomads.base.BaseApiClient
import com.evgeny.testdigitalnomads.util.mlg
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MainApiClient constructor(private val mainApi: MainApi) : BaseApiClient() {

    suspend fun getNews(pageSize: Int) = withContext(Dispatchers.IO) {
        safeApiCall { mainApi.getNews(pageSize = pageSize) }
    }


}