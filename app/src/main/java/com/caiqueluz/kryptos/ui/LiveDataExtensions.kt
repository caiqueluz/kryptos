package com.caiqueluz.kryptos.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.caiqueluz.kryptos.network.NetworkResponse
import kotlinx.coroutines.CoroutineDispatcher

fun <FROM, TO> LiveData<NetworkResponse<FROM>>.combine(
    dispatcher: CoroutineDispatcher,
    request: suspend (FROM) -> NetworkResponse<TO>
): LiveData<NetworkResponse<TO>> = this.switchMap { networkResponse ->
    liveData(dispatcher) {
        when (networkResponse) {
            is NetworkResponse.Loading -> emit(networkResponse)
            is NetworkResponse.Content -> {
                emit(value = request.invoke(networkResponse.content))
            }

            is NetworkResponse.Error -> emit(networkResponse)
        }
    }
}
