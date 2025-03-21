package eraksillan.name.mediagallery.remote

import eraksillan.name.mediagallery.architecture.NetworkResult
import eraksillan.name.mediagallery.local.model.LocalMediaPictures
import eraksillan.name.mediagallery.local.model.LocalSchedule
import eraksillan.name.mediagallery.remote.model.MediaPictures
import eraksillan.name.mediagallery.remote.model.Schedule
import javax.inject.Inject

class OnlineMediaRepository @Inject constructor(
    private val network: RetrofitMediaRepository,
) {
    suspend fun getWeeklySchedules(
        page: Int, pageSize: Int
    ): NetworkResult<LocalSchedule> {
        val result = network.getWeeklySchedules(page, pageSize)
        return toLocal<Schedule, LocalSchedule>(result)
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
