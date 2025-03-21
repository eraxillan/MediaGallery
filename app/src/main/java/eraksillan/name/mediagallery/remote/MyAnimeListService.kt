package eraksillan.name.mediagallery.remote

import eraksillan.name.mediagallery.local.model.LocalMediaPictures
import eraksillan.name.mediagallery.local.model.LocalSchedule
import eraksillan.name.mediagallery.remote.model.MediaPictures
import eraksillan.name.mediagallery.remote.model.Schedule
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// https://api.jikan.moe/v4
interface MyAnimeListService {
    // https://api.jikan.moe/v4/schedules
    @GET("schedules")
    suspend fun getWeeklySchedules(
        @Query("page") page: Int,
        @Query("limit") pageSize: Int
    ): RetrofitNetworkResult<Schedule, LocalSchedule>

    // https://api.jikan.moe/v4/anime/4459/pictures
    @GET("anime/{id}/pictures")
    suspend fun getAnimePictures(
        @Path("id") id: Int
    ): RetrofitNetworkResult<MediaPictures, LocalMediaPictures>
}
