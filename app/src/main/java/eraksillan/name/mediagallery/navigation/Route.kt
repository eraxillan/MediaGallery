package eraksillan.name.mediagallery.navigation

import eraksillan.name.mediagallery.local.model.LocalMedia
import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object MediaList : Route

    @Serializable
    data class MediaDetail(val data: LocalMedia) : Route

    @Serializable
    data class MediaExtendedDetail(val data: LocalMedia) : Route

    @Serializable
    data class MediaSeason(val year: Int, val season: LocalMedia.Season) : Route

    @Serializable
    data class FullScreenPicture(val url: String) : Route

    @Serializable
    data class MediaCast(val data: List<LocalMedia.Cast>) : Route

    @Serializable
    data class MediaStaff(val data: List<LocalMedia.Person>) : Route

    @Serializable
    data class MediaReviews(val data: List<LocalMedia.Review>) : Route

    @Serializable
    data class MediaRecommendations(val data: List<LocalMedia.Recommendation>)

    @Serializable
    data class MediaNews(val data: List<LocalMedia.NewsItem>)

    @Serializable
    data class MediaDiscussions(val data: List<LocalMedia.Discussion>)
}
