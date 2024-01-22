package com.caiqueluz.kryptos.network

sealed class NetworkResponse<out TYPE> {

    object Loading : NetworkResponse<Nothing>()

    data class Content<TYPE>(val content: TYPE) : NetworkResponse<TYPE>()

    object Error : NetworkResponse<Nothing>()
}

fun <TYPE> buildNetworkResponse(content: TYPE?): NetworkResponse<TYPE> =
    when (content != null) {
        true -> buildContentNetworkResponse(content)
        false -> buildErrorNetworkResponse()
    }

fun <TYPE> buildErrorNetworkResponse(
    throwable: Throwable = Throwable()
): NetworkResponse<TYPE> = NetworkResponse.Error

private fun <TYPE> buildContentNetworkResponse(body: TYPE): NetworkResponse<TYPE> =
    NetworkResponse.Content(content = body)
