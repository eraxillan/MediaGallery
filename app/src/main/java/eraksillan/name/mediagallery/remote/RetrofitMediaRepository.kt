package eraksillan.name.mediagallery.remote

import eraksillan.name.mediagallery.local.model.LocalMediaPictures
import eraksillan.name.mediagallery.local.model.LocalSchedule
import eraksillan.name.mediagallery.local.model.LocalSeasonList
import eraksillan.name.mediagallery.remote.model.MediaPictures
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

    suspend fun getSeasonList(): RetrofitNetworkResult<SeasonList, LocalSeasonList>

    suspend fun getMediaPictures(
        id: Int
    ): RetrofitNetworkResult<MediaPictures, LocalMediaPictures>
}
