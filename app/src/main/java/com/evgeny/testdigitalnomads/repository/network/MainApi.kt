package com.evgeny.testdigitalnomads.repository.network

import com.evgeny.testdigitalnomads.model.BaseResponse
import com.evgeny.testdigitalnomads.model.NetNews
import retrofit2.http.GET
import retrofit2.http.Query


interface MainApi {

    @GET("everything?q=android&from=2019-04-00&sortBy=publishedAt&apiKey=2759c1feacb049418975b015b3861b22")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = 5
    ): BaseResponse<List<NetNews>>


}