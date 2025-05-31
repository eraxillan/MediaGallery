package eraksillan.name.mediagallery.remote

import eraksillan.name.mediagallery.local.model.LocalMediaCast
import eraksillan.name.mediagallery.local.model.LocalMediaDiscussions
import eraksillan.name.mediagallery.local.model.LocalMediaExternalLinks
import eraksillan.name.mediagallery.local.model.LocalMediaMoreInfo
import eraksillan.name.mediagallery.local.model.LocalMediaNews
import eraksillan.name.mediagallery.local.model.LocalMediaPictures
import eraksillan.name.mediagallery.local.model.LocalMediaRecommendations
import eraksillan.name.mediagallery.local.model.LocalMediaRelations
import eraksillan.name.mediagallery.local.model.LocalMediaReviews
import eraksillan.name.mediagallery.local.model.LocalMediaStaff
import eraksillan.name.mediagallery.local.model.LocalMediaThemes
import eraksillan.name.mediagallery.local.model.LocalMediaVideos
import eraksillan.name.mediagallery.local.model.LocalSchedule
import eraksillan.name.mediagallery.local.model.LocalSeasonList
import eraksillan.name.mediagallery.remote.model.MediaCast
import eraksillan.name.mediagallery.remote.model.MediaDiscussions
import eraksillan.name.mediagallery.remote.model.MediaExternalLinks
import eraksillan.name.mediagallery.remote.model.MediaMoreInfo
import eraksillan.name.mediagallery.remote.model.MediaNews
import eraksillan.name.mediagallery.remote.model.MediaPictures
import eraksillan.name.mediagallery.remote.model.MediaRecommendations
import eraksillan.name.mediagallery.remote.model.MediaRelations
import eraksillan.name.mediagallery.remote.model.MediaReviews
import eraksillan.name.mediagallery.remote.model.MediaStaff
import eraksillan.name.mediagallery.remote.model.MediaThemes
import eraksillan.name.mediagallery.remote.model.MediaVideos
import eraksillan.name.mediagallery.remote.model.Schedule
import eraksillan.name.mediagallery.remote.model.SeasonList

interface RetrofitMediaRepository {
    suspend fun getSeasonMedias(
        year: Int,
        season: String,
        page: Int,
        pageSize: Int,
        filter: String,
        continuing: Boolean,
    ): RetrofitNetworkResult<Schedule, LocalSchedule>

    suspend fun getMediaPictures(id: Int): RetrofitNetworkResult<MediaPictures, LocalMediaPictures>

    suspend fun getMediaVideos(id: Int): RetrofitNetworkResult<MediaVideos, LocalMediaVideos>

    suspend fun getAnimeExternalLinks(id: Int): RetrofitNetworkResult<MediaExternalLinks, LocalMediaExternalLinks>

    suspend fun getAnimeRelations(id: Int): RetrofitNetworkResult<MediaRelations, LocalMediaRelations>

    suspend fun getAnimeMoreInfo(id: Int): RetrofitNetworkResult<MediaMoreInfo, LocalMediaMoreInfo>

    suspend fun getAnimeCharacters(id: Int): RetrofitNetworkResult<MediaCast, LocalMediaCast>

    suspend fun getAnimeStaff(id: Int) : RetrofitNetworkResult<MediaStaff, LocalMediaStaff>

    suspend fun getAnimeThemes(id: Int) : RetrofitNetworkResult<MediaThemes, LocalMediaThemes>

    suspend fun getAnimeReviews(id: Int) : RetrofitNetworkResult<MediaReviews, LocalMediaReviews>

    suspend fun getAnimeRecommendations(id: Int) : RetrofitNetworkResult<MediaRecommendations, LocalMediaRecommendations>

    suspend fun getAnimeNews(id: Int) : RetrofitNetworkResult<MediaNews, LocalMediaNews>

    suspend fun getAnimeDiscussions(id: Int) : RetrofitNetworkResult<MediaDiscussions, LocalMediaDiscussions>

    suspend fun getSeasonList(): RetrofitNetworkResult<SeasonList, LocalSeasonList>
}
