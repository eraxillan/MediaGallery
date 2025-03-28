package eraksillan.name.mediagallery.remote

import eraksillan.name.mediagallery.architecture.NetworkResult
import eraksillan.name.mediagallery.local.model.LocalMedia
import eraksillan.name.mediagallery.local.model.LocalMediaPictures
import eraksillan.name.mediagallery.local.model.LocalMediaTypeFilter
import eraksillan.name.mediagallery.local.model.LocalSchedule
import eraksillan.name.mediagallery.local.model.LocalSeasonList
import eraksillan.name.mediagallery.remote.model.MediaPictures
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

    suspend fun getSeasonList(): NetworkResult<LocalSeasonList> {
        val result = network.getSeasonList()
        return toLocal<SeasonList, LocalSeasonList>(result)
    }

    suspend fun getMediaPictures(
        id: Int
    ): NetworkResult<LocalMediaPictures> {
        val result = network.getMediaPictures(id)
        return toLocal<MediaPictures, LocalMediaPictures>(result)
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
