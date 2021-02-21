package com.caiqueluz.kryptos.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Response

/**
 *
 * RO - Request output
 * NRT - NetworkResponse type
 *
 * */
fun <RO, NRT> networkResponseLiveData(
    dispatcher: CoroutineDispatcher,
    request: suspend () -> (Response<RO>),
    transformation: (RO) -> NRT
): LiveData<NetworkResponse<NRT>> = liveData(dispatcher) {
    emit(NetworkResponse.Loading)

    val networkResponse = request.invoke()
        .asNetworkResponse()
        .mapContent(transformation)

    emit(networkResponse)
}

private fun <TYPE> Response<TYPE>.asNetworkResponse(): NetworkResponse<TYPE> =
    when (this.isSuccessful) {
        true -> buildNetworkResponse(content = this.body())
        false -> buildErrorNetworkResponse()
    }

private fun <FROM, TO> NetworkResponse<FROM>.mapContent(
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
