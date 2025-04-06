package eraksillan.name.mediagallery.remote

import eraksillan.name.mediagallery.local.model.LocalMediaExternalLinks
import eraksillan.name.mediagallery.local.model.LocalMediaMoreInfo
import eraksillan.name.mediagallery.local.model.LocalMediaPictures
import eraksillan.name.mediagallery.local.model.LocalMediaRelations
import eraksillan.name.mediagallery.local.model.LocalMediaVideos
import eraksillan.name.mediagallery.local.model.LocalSchedule
import eraksillan.name.mediagallery.local.model.LocalSeasonList
import eraksillan.name.mediagallery.remote.model.MediaExternalLinks
import eraksillan.name.mediagallery.remote.model.MediaMoreInfo
import eraksillan.name.mediagallery.remote.model.MediaPictures
import eraksillan.name.mediagallery.remote.model.MediaRelations
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

    suspend fun getAnimeExternalLinks(id: Int) : RetrofitNetworkResult<MediaExternalLinks, LocalMediaExternalLinks>

    suspend fun getAnimeRelations(id: Int) : RetrofitNetworkResult<MediaRelations, LocalMediaRelations>

    suspend fun getAnimeMoreInfo(id: Int) : RetrofitNetworkResult<MediaMoreInfo, LocalMediaMoreInfo>

    suspend fun getSeasonList(): RetrofitNetworkResult<SeasonList, LocalSeasonList>
}
