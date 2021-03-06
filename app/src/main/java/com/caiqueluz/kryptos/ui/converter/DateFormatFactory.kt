package com.caiqueluz.kryptos.ui.converter

import java.text.SimpleDateFormat
import java.util.*

private const val TIME_ZONE_ID = "UTC"
private const val LOCALE_LANGUAGE = "pt"
private const val LOCALE_COUNTRY = "br"

class DateFormatFactory {

    fun create(pattern: String): SimpleDateFormat {
        val locale = Locale(LOCALE_LANGUAGE, LOCALE_COUNTRY)
        val formatter = SimpleDateFormat(pattern, locale)
        formatter.timeZone = TimeZone.getTimeZone(TIME_ZONE_ID)

        return formatter
    }
}
