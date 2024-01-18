package com.caiqueluz.kryptos.ui

import androidx.lifecycle.Observer
import com.caiqueluz.kryptos.network.NetworkResponse
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
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
