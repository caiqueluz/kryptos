package com.caiqueluz.kryptos.ui.converter

import java.util.*

class TimeZoneFactory {

    fun getTimeZone(locale: Locale): TimeZone =
        Calendar
            .getInstance(locale)
            .timeZone
}
