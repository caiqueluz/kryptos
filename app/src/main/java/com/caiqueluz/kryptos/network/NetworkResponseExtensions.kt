package com.caiqueluz.kryptos.network

import androidx.lifecycle.liveData
import com.caiqueluz.kryptos.network.NetworkResponse.Companion.buildNetworkResponse
import retrofit2.Call

fun <TYPE> networkResponseLiveData(
    responseBlock: () -> NetworkResponse<TYPE>
) = liveData<NetworkResponse<TYPE>> {
    emit(NetworkResponse.Loading)

    try {
        val response = responseBlock()
        emit(response)
    } catch (exception: Exception) {
        emit(NetworkResponse.Error(throwable = Throwable()))
    }
}

fun <TYPE> Call<TYPE>.asNetworkResponse(): NetworkResponse<TYPE> {
    val response = this.execute()

    return when (response.isSuccessful) {
        true -> buildNetworkResponse(content = response.body())
        false -> buildErrorNetworkResponse()
    }
}
