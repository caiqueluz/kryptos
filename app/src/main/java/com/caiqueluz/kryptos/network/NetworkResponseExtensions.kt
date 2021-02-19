package com.caiqueluz.kryptos.network

import retrofit2.Response

fun <TYPE> Response<TYPE>.asNetworkResponse(): NetworkResponse<TYPE> =
    when (this.isSuccessful) {
        true -> buildNetworkResponse(content = this.body())
        false -> buildErrorNetworkResponse()
    }

fun <FROM, TO> NetworkResponse<FROM>.mapContent(
    transformation: (FROM) -> TO
): NetworkResponse<TO> = when (this) {
    is NetworkResponse.Loading -> this
    is NetworkResponse.Content -> NetworkResponse.Content(
        content = transformation.invoke(this.content)
    )
    is NetworkResponse.Error -> {
        error.cause?.printStackTrace()
        NetworkResponse.Error(error = error)
    }
}
