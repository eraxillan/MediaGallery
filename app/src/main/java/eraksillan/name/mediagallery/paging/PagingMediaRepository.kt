package eraksillan.name.mediagallery.paging

import eraksillan.name.mediagallery.BuildConfig
import eraksillan.name.mediagallery.architecture.NetworkResult
import eraksillan.name.mediagallery.local.model.LocalMedia
import eraksillan.name.mediagallery.local.model.LocalMediaExternalLinks
import eraksillan.name.mediagallery.local.model.LocalMediaMoreInfo
import eraksillan.name.mediagallery.local.model.LocalMediaPictures
import eraksillan.name.mediagallery.local.model.LocalMediaRelations
import eraksillan.name.mediagallery.local.model.LocalMediaTypeFilter
import eraksillan.name.mediagallery.local.model.LocalMediaVideos
import eraksillan.name.mediagallery.local.model.LocalSchedule
import eraksillan.name.mediagallery.local.model.LocalSeasonList
import eraksillan.name.mediagallery.remote.OnlineMediaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

// https://proandroiddev.com/pagination-in-jetpack-compose-with-and-without-paging-3-e45473a352f4#622d
class PagingMediaRepository @Inject constructor(
    private val repository: OnlineMediaRepository
) {
    fun getSeasonMedias(
        year: Int,
        season: LocalMedia.Season,
        page: Int,
        pageSize: Int,
        filter: LocalMediaTypeFilter,
        continuing: Boolean
    ): Flow<NetworkResult<LocalSchedule>> = flow {
        if (BuildConfig.DEBUG) {
            delay(2_000)
        }

        emit(repository.getSeasonMedias(year, season, page, pageSize, filter, continuing))
    }.flowOn(Dispatchers.IO)

    fun getMediaPictures(id: Int): Flow<NetworkResult<LocalMediaPictures>> = flow {
        emit(repository.getMediaPictures(id))
    }.flowOn(Dispatchers.IO)

    fun getMediaVideos(id: Int): Flow<NetworkResult<LocalMediaVideos>> = flow {
        emit(repository.getAnimeVideos(id))
    }.flowOn(Dispatchers.IO)

    fun getMediaExternalLinks(id: Int): Flow<NetworkResult<LocalMediaExternalLinks>> = flow {
        emit(repository.getAnimeExternalLinks(id))
    }

    fun getMediaRelations(id: Int): Flow<NetworkResult<LocalMediaRelations>> = flow {
        emit(repository.getAnimeRelations(id))
    }

    fun getMediaMoreInfo(id: Int): Flow<NetworkResult<LocalMediaMoreInfo>> = flow {
        emit(repository.getAnimeMoreInfo(id))
    }

    fun getSeasonList(): Flow<NetworkResult<LocalSeasonList>> = flow {
        if (BuildConfig.DEBUG) {
            delay(2_000)
        }

        emit(repository.getSeasonList())
    }.flowOn(Dispatchers.IO)
}
