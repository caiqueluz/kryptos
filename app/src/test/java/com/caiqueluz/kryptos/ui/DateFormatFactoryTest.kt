package com.caiqueluz.kryptos.ui

import com.caiqueluz.kryptos.ui.converter.DateFormatFactory
import com.caiqueluz.kryptos.ui.converter.TimeZoneFactory
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*

@RunWith(JUnit4::class)
class DateFormatFactoryTest {

    private val fakeTimeZoneFactory = TimeZoneFactory()
    private val factory = DateFormatFactory(fakeTimeZoneFactory)

    @Test
    fun whenCreateIsCalled_verifyResponseHasCorrectPattern() {
        val expected = "dd/mm/yyyy"
        val actual = factory.create("dd/mm/yyyy").toPattern()

        assertEquals(expected, actual)
    }

    @Test
    fun whenCreateIsCalled_verifyResponseHasCorrectTimeZoneId() {
        val locale = Locale("pt", "br")

        val expected = fakeTimeZoneFactory.getTimeZone(locale).id
        val actual = factory.create("dd/mm/yyy").timeZone.id

        assertEquals(expected, actual)
    }
}
