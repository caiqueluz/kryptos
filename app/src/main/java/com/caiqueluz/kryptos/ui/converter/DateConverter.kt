package com.caiqueluz.kryptos.ui.converter

import java.text.ParseException

private const val TIME_ZONE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

class DateConverter(
    private val formatFactory: DateFormatFactory
) {

    fun convert(value: String, pattern: String): String? {
        val timeZoneFormatter = formatFactory.create(TIME_ZONE_PATTERN)
        val dateFormatter = formatFactory.create(pattern)

        val timeZoneDate = try {
            timeZoneFormatter.parse(value)
        } catch (exception: ParseException) {
            throw IllegalArgumentException("Error when parsing date from value: $value")
        }

        return dateFormatter.format(timeZoneDate)
    }
}
