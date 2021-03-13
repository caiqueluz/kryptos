package com.caiqueluz.kryptos.ui.converter

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

private const val LOCALE_LANGUAGE = "pt"
private const val LOCALE_COUNTRY = "br"

class DateFormatFactory @Inject constructor(
    private val timeZoneFactory: TimeZoneFactory
) {

    fun create(pattern: String): SimpleDateFormat {
        val locale = Locale(LOCALE_LANGUAGE, LOCALE_COUNTRY)
        val formatter = SimpleDateFormat(pattern, locale)

        val timeZone = timeZoneFactory.getTimeZone(locale)
        formatter.timeZone = timeZone

        return formatter
    }
}
