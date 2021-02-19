package com.caiqueluz.kryptos.network

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import retrofit2.Response

/**
 *
 * RI - Request input
 * RO - Request output
 * NRT - NetworkResponse type
 *
 * */
class NetworkResponseLiveData<RI, RO, NRT>(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val request: suspend (RI) -> (Response<RO>),
    private val transformation: (RO) -> NRT
) : LiveData<NetworkResponse<NRT>>() {

    private val scope = CoroutineScope(Job() + dispatcher)

    fun start(requestInput: RI) {
        postValue(NetworkResponse.Loading)

        scope.launch {
            val networkResponse = request.invoke(requestInput)
                .asNetworkResponse()
                .mapContent(transformation)

            postValue(networkResponse)
        }
    }
}
