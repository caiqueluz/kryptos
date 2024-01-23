package com.caiqueluz.kryptos.network

sealed class NetworkResponse<out TYPE> {

    data object Loading : NetworkResponse<Nothing>()

    data class Content<TYPE>(val content: TYPE) : NetworkResponse<TYPE>()

    data object Error : NetworkResponse<Nothing>()
}
