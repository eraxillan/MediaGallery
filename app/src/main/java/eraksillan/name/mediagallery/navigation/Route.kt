package eraksillan.name.mediagallery.navigation

import eraksillan.name.mediagallery.local.model.LocalMedia
import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object MediaList : Route

    @Serializable
    data class MediaDetail(val data: LocalMedia) : Route

    @Serializable
    data class Season(val year: Int, val season: LocalMedia.Season) : Route
}
