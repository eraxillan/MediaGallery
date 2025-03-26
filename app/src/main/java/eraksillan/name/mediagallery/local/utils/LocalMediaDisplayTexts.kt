package eraksillan.name.mediagallery.local.utils

import eraksillan.name.mediagallery.local.model.LocalMedia
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.MonthNames.Companion.ENGLISH_ABBREVIATED
import kotlinx.datetime.format.char
import java.util.Locale

fun LocalMedia.displayDefaultTitle(): String {
    return titles.firstOrNull {
        it.type == LocalMedia.Title.Type.DEFAULT
    }?.title ?: ""
}

fun LocalMedia.displayGenresText(): String {
    val displayThemes = themes.joinToString(", ") { it.name }
    val displayDemographics = demographics.joinToString(", ") { it.name }

    var displayGenres = genres.joinToString(", ") { it.name }
    if (themes.isNotEmpty()) {
        if (displayGenres.isNotEmpty()) {
            displayGenres += ", "
        }
        displayGenres += displayThemes
    }
    if (demographics.isNotEmpty()) {
        if (displayGenres.isNotEmpty()) {
            displayGenres += ", "
        }
        displayGenres += displayDemographics
    }

    return displayGenres
}

fun LocalMedia.displayStartDateText(): String {
    // Example: Mar 26, 2025
    val dateCustomFormat = DateTimeComponents.Format {
        monthName(ENGLISH_ABBREVIATED)
        char(' ')
        dayOfMonth()
        char(',')
        char(' ')
        year()
    }
    return aired.from?.format(dateCustomFormat) ?: "N/A"
}

fun LocalMedia.scoreDisplayText(): String {
    return if (score != null) String.format(Locale.GERMANY, "%-,2.2f", score) else "N/A"
}

fun LocalMedia.membersDisplayText(): String {
    return if (members != null) String.format(Locale.forLanguageTag("RU"), "%,d", members) else "N/A"
}
