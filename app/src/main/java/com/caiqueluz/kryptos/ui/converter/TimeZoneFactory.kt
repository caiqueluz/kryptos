package com.caiqueluz.kryptos.ui.converter

import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class TimeZoneFactory {

    fun getTimeZone(locale: Locale): TimeZone =
        Calendar
            .getInstance(locale)
            .timeZone
}
