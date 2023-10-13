package com.caiqueluz.kryptos.ui.converter

import java.text.SimpleDateFormat
import java.util.Locale

private const val LOCALE_LANGUAGE = "pt"
private const val LOCALE_COUNTRY = "br"

class DateFormatFactory(
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
