package com.evgeny.testdigitalnomads.base

import com.evgeny.testdigitalnomads.R
import com.evgeny.testdigitalnomads.util.getStringRes
import com.evgeny.testdigitalnomads.util.isConnected
import com.evgeny.testdigitalnomads.util.mlg
import com.evgeny.testdigitalnomads.model.BaseResponse
import retrofit2.HttpException
import ru.vippolis.employeecontrol.repository.Resource
import java.net.SocketTimeoutException

open class BaseApiClient {

    private val CALL_TAG = "safeApiCall"

    //==============================================================================================

    suspend fun <T> safeApiCall(apiCall: suspend () -> BaseResponse<T>): Resource<T> {
        return if (isConnected()) {
            try {
                val response = apiCall.invoke()
                checkResponse(response)

            } catch (e: Throwable) {
                checkException(e)
            }
        } else Resource.Error(getStringRes(R.string.internet_disconnected))
    }

    private fun <T> checkResponse(response: BaseResponse<T>): Resource<T> {
        val responseOk = response.result.response ?: false
        return if (responseOk) {
            Resource.Success(response.result.result)
        } else {
            val errCode = response.result.error_code
            val errMsg = response.result.error_msg
            Resource.Error(errMsg)
        }
    }

    private fun checkException(e: Throwable): Resource.Error {
        return when (e) {
            is HttpException -> {
                mlg(
                    "HttpException: ${e.message()}",
                    CALL_TAG
                )
                Resource.Error("${e.code()} ${e.message()}")
            }

            is SocketTimeoutException -> {
                mlg(
                    "SocketTimeoutException: ${e.message}",
                    CALL_TAG
                )
                Resource.Error(getStringRes(R.string.internet_timeout))
            }

            else -> {
                mlg(
                    "Throwable: ${e.message}",
                    CALL_TAG
                )
                Resource.Error(getStringRes(R.string.internet_something_went_wrong))
            }
        }
    }

}