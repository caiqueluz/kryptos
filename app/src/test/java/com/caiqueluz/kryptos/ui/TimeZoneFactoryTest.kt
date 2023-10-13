package com.caiqueluz.kryptos.ui

import com.caiqueluz.kryptos.ui.converter.TimeZoneFactory
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.Locale
import java.util.Calendar

@RunWith(JUnit4::class)
class TimeZoneFactoryTest {

    private val factory = TimeZoneFactory()

    @Test
    fun whenGetTimeZoneIsCalled_verifyResponseIsCorrect() {
        val locale = Locale("pt", "br")

        val expected = factory.getTimeZone(locale)
        val actual = Calendar.getInstance(locale).timeZone

        assertEquals(expected, actual)
    }
}
