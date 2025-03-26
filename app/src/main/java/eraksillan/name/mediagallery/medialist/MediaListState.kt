package eraksillan.name.mediagallery.medialist

import eraksillan.name.mediagallery.local.model.LocalMedia
import eraksillan.name.mediagallery.local.model.LocalMediaSortType
import eraksillan.name.mediagallery.local.model.LocalMediaTypeFilter
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class MediaListState(
    val type: LocalMediaTypeFilter,
    val season: LocalMedia.Season,
    val year: Int,
    val sortType: LocalMediaSortType,
) {
    companion object {
        fun getDefault(season: LocalMedia.Season): MediaListState {
            return MediaListState(
                year = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).year,
                season = season,
                type = LocalMediaTypeFilter.TV_NEW,
                sortType = LocalMediaSortType.BY_MEMBER_COUNT
            )
        }
    }
}
