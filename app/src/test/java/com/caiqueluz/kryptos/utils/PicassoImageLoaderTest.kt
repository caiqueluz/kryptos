package com.caiqueluz.kryptos.utils

import android.graphics.Bitmap
import com.caiqueluz.kryptos.ConcurrentTest
import com.nhaarman.mockitokotlin2.*
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class PicassoImageLoaderTest : ConcurrentTest() {

    private val mockResponse = mock<Bitmap>()

    private val mockRequestCreator = mock<RequestCreator> {
        on { get() } doReturn mockResponse
    }

    private val spyPicasso = mock<Picasso> {
        on { load(any<String>()) } doReturn mockRequestCreator
    }

    private val imageLoader = PicassoImageLoader(testDispatcher, spyPicasso)

    @Test
    fun whenLoadImageIsCalled_WithUrl_verifyPicassoIsCalledWithCorrectUrl() {
        val expected = "imageurl.com"

        imageLoader.loadImage(expected)

        verify(spyPicasso).load(expected)
    }

    @Test
    fun whenLoadImageIsCalled_verifyResponseIsCorrect() {
        val response = imageLoader.loadImage("imageurl.com")

        assertEquals(mockResponse, response)
    }
}
