package com.caiqueluz.kryptos.network

import retrofit2.Response

fun <FROM, TO> Response<FROM>.mapResponse(
    transformation: (FROM) -> TO
): NetworkResponse<TO> = this.asNetworkResponse()
    .mapContent {
        transformation.invoke(it)
    }

fun <FROM, TO> NetworkResponse<FROM>.mapContent(
    transformation: (FROM) -> TO
): NetworkResponse<TO> = when (this) {
    is NetworkResponse.Loading -> this
    is NetworkResponse.Content -> NetworkResponse.Content(
        content = transformation.invoke(this.content)
    )
    is NetworkResponse.Error -> NetworkResponse.Error(
        error = this.error
    )
}

private fun <TYPE> Response<TYPE>.asNetworkResponse(): NetworkResponse<TYPE> =
    when (this.isSuccessful) {
        true -> buildNetworkResponse(content = this.body())
        false -> buildErrorNetworkResponse()
    }
