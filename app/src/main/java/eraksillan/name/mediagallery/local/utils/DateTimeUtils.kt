package eraksillan.name.mediagallery.local.utils

import eraksillan.name.mediagallery.remote.model.parseOrNull
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.MonthNames.Companion.ENGLISH_ABBREVIATED
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime

fun currentYear(): Int {
    return Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .year
}

fun currentMonth(): Int {
    return Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .monthNumber
}

fun monthAndDayText(date: String?): String {
    // Example: Mar 26, 2025
    val dateCustomFormat = DateTimeComponents.Format {
        monthName(ENGLISH_ABBREVIATED)
        char(' ')
        dayOfMonth()
        char(',')
        char(' ')
        year()
    }
    return Instant.parseOrNull(date)?.format(dateCustomFormat) ?: "N/A"
}

fun formatReviewTime(input: String?): String {
    // Jan 18, 8:28 AM
    val customFormat = DateTimeComponents.Format {
        monthName(ENGLISH_ABBREVIATED)
        char(' ')
        dayOfMonth()
        char(',')
        char(' ')
        amPmHour()
        char(':')
        minute()
        char(' ')
        amPmMarker("AM", "PM")
    }
    return Instant.parseOrNull(input)?.format(customFormat) ?: "N/A"
}
