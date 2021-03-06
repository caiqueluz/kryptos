package com.caiqueluz.kryptos.ui.converter

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

private const val TIME_ZONE_ID = "UTC"

class DateConverter @Inject constructor(
    private val formatter: SimpleDateFormat
) {

    fun convertDate(value: String, pattern: String): String? {
        formatter.timeZone = TimeZone.getTimeZone(TIME_ZONE_ID)

        return try {
            val date = formatter.parse(value)
            formatter.applyPattern(pattern)

            formatter.format(date)
        } catch (exception: ParseException) {
            throw IllegalArgumentException("Error when parsing date from value: $value")
        }
    }
}
