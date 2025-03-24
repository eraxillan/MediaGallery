package eraksillan.name.mediagallery.remote

import eraksillan.name.mediagallery.local.model.LocalMediaPictures
import eraksillan.name.mediagallery.local.model.LocalSchedule
import eraksillan.name.mediagallery.remote.model.MediaPictures
import eraksillan.name.mediagallery.remote.model.Schedule

interface RetrofitMediaRepository {
    suspend fun getSeasonMedias(
        year: Int,
        season: String,
        page: Int,
        pageSize: Int,
        filter: String,
        continuing: Boolean,
    ): RetrofitNetworkResult<Schedule, LocalSchedule>

    suspend fun getMediaPictures(
        id: Int
    ): RetrofitNetworkResult<MediaPictures, LocalMediaPictures>
}
