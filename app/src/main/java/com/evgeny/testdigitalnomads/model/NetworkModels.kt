package com.evgeny.testdigitalnomads.model

import com.google.gson.annotations.SerializedName



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


// REQUEST ↓ ↓ ↓ ===================================================================================
data class Auth(
    @SerializedName("user") val login: String,
    @SerializedName("pass") val pass: String
)


// RESPONSE ↓ ↓ ↓ ==================================================================================
data class AuthResult(
    @SerializedName("userID") val userID: String,
    @SerializedName("userFIO") val userFIO: String,
    @SerializedName("profession") val profession: String
)
