package eraksillan.name.mediagallery.local.utils

import eraksillan.name.mediagallery.R
import eraksillan.name.mediagallery.local.model.LocalMedia.Season
import eraksillan.name.mediagallery.local.model.LocalMedia.Season.FALL
import eraksillan.name.mediagallery.local.model.LocalMedia.Season.SPRING
import eraksillan.name.mediagallery.local.model.LocalMedia.Season.SUMMER
import eraksillan.name.mediagallery.local.model.LocalMedia.Season.UNKNOWN
import eraksillan.name.mediagallery.local.model.LocalMedia.Season.WINTER

fun getCurrentSeason(): Season {

    return when (currentMonth()) {
        12, 1, 2 -> WINTER
        3, 4, 5 -> SPRING
        6, 7, 8 -> SUMMER
        9, 10, 11 -> FALL
        else -> UNKNOWN
    }
}

fun getPreviousSeason(): Season {
    return when (getCurrentSeason()) {
        WINTER -> FALL
        SPRING -> WINTER
        SUMMER -> SPRING
        FALL -> SUMMER
        else -> UNKNOWN
    }
}

fun getNextSeason(): Season {
    return when (getCurrentSeason()) {
        WINTER -> SPRING
        SPRING -> SUMMER
        SUMMER -> FALL
        FALL -> WINTER
        else -> UNKNOWN
    }
}

fun getRemainingSeason(): Season {
    return when (getNextSeason()) {
        WINTER -> SPRING
        SPRING -> SUMMER
        SUMMER -> FALL
        FALL -> WINTER
        else -> UNKNOWN
    }
}

fun getSeasonTriple(): MediaSeasonInfoTriple? {
    val currentYear = currentYear()
    return when (getCurrentSeason()) {
        WINTER -> MediaSeasonInfoTriple(
            data = listOf(
                MediaSeasonInfo(
                    value = FALL,
                    year = currentYear,
                    tabTitleResId = R.string.fall
                ),
                MediaSeasonInfo(
                    value = WINTER,
                    year = currentYear,
                    tabTitleResId = R.string.winter
                ),
                MediaSeasonInfo(
                    value = SPRING,
                    year = currentYear,
                    tabTitleResId = R.string.spring
                )
            )
        )

        SPRING -> MediaSeasonInfoTriple(
            data = listOf(
                MediaSeasonInfo(
                    value = WINTER,
                    year = currentYear,
                    tabTitleResId = R.string.winter
                ),
                MediaSeasonInfo(
                    value = SPRING,
                    year = currentYear,
                    tabTitleResId = R.string.spring
                ),
                MediaSeasonInfo(
                    value = SUMMER,
                    year = currentYear,
                    tabTitleResId = R.string.summer
                )
            )
        )

        SUMMER -> MediaSeasonInfoTriple(
            data = listOf(
                MediaSeasonInfo(
                    value = SPRING,
                    year = currentYear,
                    tabTitleResId = R.string.spring
                ),
                MediaSeasonInfo(
                    value = SUMMER,
                    year = currentYear,
                    tabTitleResId = R.string.summer
                ),
                MediaSeasonInfo(
                    value = FALL,
                    year = currentYear,
                    tabTitleResId = R.string.fall
                )
            )
        )

        FALL -> MediaSeasonInfoTriple(
            listOf(
                MediaSeasonInfo(
                    value = SUMMER,
                    year = currentYear,
                    tabTitleResId = R.string.summer
                ),
                MediaSeasonInfo(
                    value = FALL,
                    year = currentYear,
                    tabTitleResId = R.string.fall
                ),
                MediaSeasonInfo(
                    value = WINTER,
                    year = currentYear,
                    tabTitleResId = R.string.winter
                )
            )
        )

        UNKNOWN -> null
    }
}

data class MediaSeasonInfo(
    val value: Season,
    val year: Int,
    val tabTitleResId: Int,
)

data class MediaSeasonInfoTriple(
    val data: List<MediaSeasonInfo>
)
