package com.evgeny.testdigitalnomads.repository.network

import com.evgeny.testdigitalnomads.model.BaseResponse
import com.evgeny.testdigitalnomads.model.NetNews
import retrofit2.http.GET
import retrofit2.http.Query


interface MainApi {

    //    @GET("./{page}")
    @GET("everything?q=android&from=2019-04-00&sortBy=publishedAt&apiKey=26eddb253e7840f988aec61f2ece2907")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): BaseResponse<List<NetNews>>


}