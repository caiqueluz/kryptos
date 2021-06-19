package com.caiqueluz.kryptos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.caiqueluz.kryptos.network.NetworkResponse
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertTrue
import retrofit2.Response

inline fun <reified TYPE> successResponse(): Response<TYPE> =
    Response.success(mock())

fun <TYPE> errorResponse(): Response<TYPE> = Response.error(404, mock())

fun <TYPE> networkResponseObserver(): Observer<NetworkResponse<TYPE>> = mock()

fun <TYPE> verifyLoadingResponse(observer: Observer<NetworkResponse<TYPE>>) {
    val captor = argumentCaptor<NetworkResponse<TYPE>>()
    verify(observer, times(2)).onChanged(captor.capture())

    assertTrue(captor.firstValue is NetworkResponse.Loading)
}

fun <TYPE> verifyContentResponse(observer: Observer<NetworkResponse<TYPE>>) {
    val captor = argumentCaptor<NetworkResponse<TYPE>>()
    verify(observer, times(2)).onChanged(captor.capture())

    assertTrue(captor.lastValue is NetworkResponse.Content)
}

fun <TYPE> verifyErrorResponse(observer: Observer<NetworkResponse<TYPE>>) {
    val captor = argumentCaptor<NetworkResponse<TYPE>>()
    verify(observer, times(2)).onChanged(captor.capture())

    assertTrue(captor.lastValue is NetworkResponse.Error)
}

fun <TYPE> Ongoing

fun <TYPE> LiveData<NetworkResponse<TYPE>>.mockLoading() =
    MutableLiveData(NetworkResponse.Loading)

fun <TYPE> LiveData<NetworkResponse<TYPE>>.mockContent(
    content: NetworkResponse.Content<TYPE>
) = MutableLiveData(content)

fun <TYPE> LiveData<NetworkResponse<TYPE>>.mockError(
    error: NetworkResponse.Error
) = MutableLiveData(error)
