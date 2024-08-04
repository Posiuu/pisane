package com.pisane.pisane.helpers

import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class DatetimeHelper() {
    companion object {
        fun secondsDiff(datetimeStr: String): Long {
            val dateFormatter: DateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val now = dateFormatter
                .withZone(ZoneOffset.UTC)
                .format(Instant.now())
            val from = LocalDateTime.parse(datetimeStr, dateFormatter)
            val to = LocalDateTime.parse(now, dateFormatter)

            val timezoneDiff = (60 * 60 * 2)
            return Duration.between(from, to).seconds + timezoneDiff
        }
    }
}
