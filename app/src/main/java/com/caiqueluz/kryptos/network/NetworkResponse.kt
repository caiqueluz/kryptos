package com.caiqueluz.kryptos.network

sealed class NetworkResponse<out TYPE> {

    object Loading : NetworkResponse<Nothing>()

    data class Content<TYPE>(val content: TYPE) : NetworkResponse<TYPE>()

    object Error : NetworkResponse<Nothing>()
}
