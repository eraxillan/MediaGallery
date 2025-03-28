package eraksillan.name.mediagallery.local.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
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
