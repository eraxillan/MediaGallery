package eraksillan.name.mediagallery.remote

import eraksillan.name.mediagallery.architecture.NetworkResult
import eraksillan.name.mediagallery.local.model.LocalMedia
import eraksillan.name.mediagallery.local.model.LocalMediaCast
import eraksillan.name.mediagallery.local.model.LocalMediaExternalLinks
import eraksillan.name.mediagallery.local.model.LocalMediaMoreInfo
import eraksillan.name.mediagallery.local.model.LocalMediaPictures
import eraksillan.name.mediagallery.local.model.LocalMediaRecommendations
import eraksillan.name.mediagallery.local.model.LocalMediaRelations
import eraksillan.name.mediagallery.local.model.LocalMediaReviews
import eraksillan.name.mediagallery.local.model.LocalMediaStaff
import eraksillan.name.mediagallery.local.model.LocalMediaThemes
import eraksillan.name.mediagallery.local.model.LocalMediaTypeFilter
import eraksillan.name.mediagallery.local.model.LocalMediaVideos
import eraksillan.name.mediagallery.local.model.LocalSchedule
import eraksillan.name.mediagallery.local.model.LocalSeasonList
import eraksillan.name.mediagallery.remote.model.MediaCast
import eraksillan.name.mediagallery.remote.model.MediaExternalLinks
import eraksillan.name.mediagallery.remote.model.MediaMoreInfo
import eraksillan.name.mediagallery.remote.model.MediaPictures
import eraksillan.name.mediagallery.remote.model.MediaRecommendations
import eraksillan.name.mediagallery.remote.model.MediaRelations
import eraksillan.name.mediagallery.remote.model.MediaReviews
import eraksillan.name.mediagallery.remote.model.MediaStaff
import eraksillan.name.mediagallery.remote.model.MediaThemes
import eraksillan.name.mediagallery.remote.model.MediaVideos
import eraksillan.name.mediagallery.remote.model.Schedule
import eraksillan.name.mediagallery.remote.model.SeasonList
import javax.inject.Inject

class OnlineMediaRepository @Inject constructor(
    private val network: RetrofitMediaRepository,
) {
    suspend fun getSeasonMedias(
        year: Int,
        season: LocalMedia.Season,
        page: Int,
        pageSize: Int,
        filter: LocalMediaTypeFilter,
        continuing: Boolean
    ): NetworkResult<LocalSchedule> {
        val result = network.getSeasonMedias(
            year = year,
            season = season.query,
            page = page,
            pageSize = pageSize,
            filter = filter.query,
            continuing = continuing
        )
        return toLocal<Schedule, LocalSchedule>(result)
    }

    suspend fun getMediaPictures(
        id: Int
    ): NetworkResult<LocalMediaPictures> {
        val result = network.getMediaPictures(id)
        return toLocal<MediaPictures, LocalMediaPictures>(result)
    }

    suspend fun getAnimeVideos(id: Int): NetworkResult<LocalMediaVideos> {
        val result = network.getMediaVideos(id)
        return toLocal<MediaVideos, LocalMediaVideos>(result)
    }

    suspend fun getAnimeExternalLinks(id: Int): NetworkResult<LocalMediaExternalLinks> {
        val result = network.getAnimeExternalLinks(id)
        return toLocal<MediaExternalLinks, LocalMediaExternalLinks>(result)
    }

    suspend fun getAnimeRelations(id: Int): NetworkResult<LocalMediaRelations> {
        val result = network.getAnimeRelations(id)
        return toLocal<MediaRelations, LocalMediaRelations>(result)
    }

    suspend fun getAnimeMoreInfo(id: Int): NetworkResult<LocalMediaMoreInfo> {
        val result = network.getAnimeMoreInfo(id)
        return toLocal<MediaMoreInfo, LocalMediaMoreInfo>(result)
    }

    suspend fun getAnimeCharacters(id: Int): NetworkResult<LocalMediaCast> {
        val result = network.getAnimeCharacters(id)
        return toLocal<MediaCast, LocalMediaCast>(result)
    }

    suspend fun getAnimeStaff(id: Int): NetworkResult<LocalMediaStaff> {
        val result = network.getAnimeStaff(id)
        return toLocal<MediaStaff, LocalMediaStaff>(result)
    }

    suspend fun getAnimeThemes(id: Int) : NetworkResult<LocalMediaThemes> {
        val result = network.getAnimeThemes(id)
        return toLocal<MediaThemes, LocalMediaThemes>(result)
    }

    suspend fun getAnimeReviews(id: Int) : NetworkResult<LocalMediaReviews> {
        val result = network.getAnimeReviews(id)
        return toLocal<MediaReviews, LocalMediaReviews>(result)
    }

    suspend fun getAnimeRecommendations(id: Int) : NetworkResult<LocalMediaRecommendations> {
        val result = network.getAnimeRecommendations(id)
        return toLocal<MediaRecommendations, LocalMediaRecommendations>(result)
    }

    suspend fun getSeasonList(): NetworkResult<LocalSeasonList> {
        val result = network.getSeasonList()
        return toLocal<SeasonList, LocalSeasonList>(result)
    }
}

fun <T1 : RetrofitResponse<T2>, T2 : Any> toLocal(
    input: RetrofitNetworkResult<T1, T2>
): NetworkResult<T2> {
    return when (input) {
        is RetrofitNetworkResult.Success -> NetworkResult.Success<T2>(input.data.toLocal())
        is RetrofitNetworkResult.Error -> NetworkResult.Error<T2>(input.code, input.message)
        is RetrofitNetworkResult.Exception -> NetworkResult.Exception<T2>(input.e)
    }
}
