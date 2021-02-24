package com.caiqueluz.kryptos.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.caiqueluz.kryptos.network.NetworkResponse
import com.caiqueluz.kryptos.network.buildErrorNetworkResponse
import com.caiqueluz.kryptos.network.buildNetworkResponse
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Response

fun <TYPE> networkResponseLiveData(
    dispatcher: CoroutineDispatcher,
    request: suspend () -> NetworkResponse<TYPE>
): LiveData<NetworkResponse<TYPE>> = liveData(dispatcher) {
    emit(NetworkResponse.Loading)

    val networkResponse = request.invoke()
    emit(networkResponse)
}

fun <FROM, SRI, TO> LiveData<NetworkResponse<FROM>>.combine(
    inputTransformation: (FROM) -> SRI,
    request: suspend (SRI) -> NetworkResponse<TO>
): LiveData<NetworkResponse<TO>> = this.switchMap { networkResponse ->
    liveData {
        when (networkResponse) {
            is NetworkResponse.Loading -> emit(networkResponse)
            is NetworkResponse.Content -> {
                var input: SRI? = null

                networkResponse.mapContent {
                    input = inputTransformation.invoke(it)
                }

                input?.let { emit(request.invoke(it)) }
            }
            is NetworkResponse.Error -> emit(networkResponse)
        }
    }
}

fun <FROM, TO> Response<FROM>.mapResponse(
    transformation: (FROM) -> TO
): NetworkResponse<TO> = this.asNetworkResponse().mapContent {
    transformation.invoke(it)
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
    is NetworkResponse.Error -> NetworkResponse.Error(
        error = this.error
    )
}
