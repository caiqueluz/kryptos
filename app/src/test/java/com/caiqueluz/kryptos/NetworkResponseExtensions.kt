package com.caiqueluz.kryptos

import androidx.lifecycle.Observer
import com.caiqueluz.kryptos.network.NetworkResponse
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertTrue

fun <TYPE> verifyLoadingResponse(
    observer: Observer<NetworkResponse<TYPE>>
) {
    val captor = argumentCaptor<NetworkResponse<TYPE>>()
    verify(observer, times(2)).onChanged(captor.capture())

    assertTrue(captor.firstValue is NetworkResponse.Loading)
}

fun <TYPE> verifyContentResponse(
    observer: Observer<NetworkResponse<TYPE>>
) {
    val captor = argumentCaptor<NetworkResponse<TYPE>>()
    verify(observer, times(2)).onChanged(captor.capture())

    assertTrue(captor.lastValue is NetworkResponse.Content)
}

fun <TYPE> verifyErrorResponse(
    observer: Observer<NetworkResponse<TYPE>>
) {
    val captor = argumentCaptor<NetworkResponse<TYPE>>()
    verify(observer, times(2)).onChanged(captor.capture())

    assertTrue(captor.lastValue is NetworkResponse.Error)
}
