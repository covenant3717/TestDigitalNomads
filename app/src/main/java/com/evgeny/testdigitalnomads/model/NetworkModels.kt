package com.evgeny.testdigitalnomads.model

import com.google.gson.annotations.SerializedName




data class BaseResponse<T>(
    @SerializedName("status") val status: String?,
    @SerializedName("articles") val articles: T?,
    @SerializedName("code") val errCode: String?,
    @SerializedName("message") val errMessage: String?
)


// REQUEST ↓ ↓ ↓ ===================================================================================


// RESPONSE ↓ ↓ ↓ ==================================================================================
data class NetNews(
    @SerializedName("publishedAt") val date: String?,
    @SerializedName("urlToImage") val imageUrl: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("url") val url: String?
)
