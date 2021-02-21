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
