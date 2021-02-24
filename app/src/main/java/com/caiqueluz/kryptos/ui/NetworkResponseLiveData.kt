package com.caiqueluz.kryptos.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.caiqueluz.kryptos.network.NetworkResponse
import kotlinx.coroutines.CoroutineDispatcher

fun <TYPE> networkResponseLiveData(
    dispatcher: CoroutineDispatcher,
    request: suspend () -> NetworkResponse<TYPE>
): LiveData<NetworkResponse<TYPE>> = liveData(dispatcher) {
    emit(NetworkResponse.Loading)

    val networkResponse = request.invoke()
    emit(networkResponse)
}
