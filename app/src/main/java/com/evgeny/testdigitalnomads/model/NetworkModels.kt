package com.evgeny.testdigitalnomads.model

import com.google.gson.annotations.SerializedName



/*
data class BaseResponse<T>(
    @SerializedName("result") val result: SubBaseResponse<T>
) {
    data class SubBaseResponse<T> @JvmOverloads constructor(
        @SerializedName("response") val response: Boolean = false,
        @SerializedName("result") var result: T,
        @SerializedName("error_code") val error_code: Int,
        @SerializedName("error_msg") val error_msg: String
    )
}
*/

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
