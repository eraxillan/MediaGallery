package eraksillan.name.mediagallery.paging

import eraksillan.name.mediagallery.BuildConfig
import eraksillan.name.mediagallery.architecture.NetworkResult
import eraksillan.name.mediagallery.local.model.LocalMedia
import eraksillan.name.mediagallery.local.model.LocalMediaTypeFilter
import eraksillan.name.mediagallery.local.model.LocalSchedule
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
}
