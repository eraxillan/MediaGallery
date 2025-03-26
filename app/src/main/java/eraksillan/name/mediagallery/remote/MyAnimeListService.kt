package eraksillan.name.mediagallery.remote

import eraksillan.name.mediagallery.local.model.LocalMediaPictures
import eraksillan.name.mediagallery.local.model.LocalSchedule
import eraksillan.name.mediagallery.remote.model.MediaPictures
import eraksillan.name.mediagallery.remote.model.Schedule
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MyAnimeListService {
    // https://api.jikan.moe/v4/seasons/{year}/{season}
    @GET("seasons/{year}/{season}")
    suspend fun getSeasonMedias(
        @Path("year") year: Int,
        @Path("season") season: String,
        @Query("page") page: Int,
        @Query("limit") pageSize: Int,
        @Query("filter") filter: String,
        @Query("continuing") continuing: Boolean,
        @Query("sfw") safeForWork: Boolean = true
    ): RetrofitNetworkResult<Schedule, LocalSchedule>

    // https://api.jikan.moe/v4/anime/4459/pictures
    @GET("anime/{id}/pictures")
    suspend fun getAnimePictures(
        @Path("id") id: Int
    ): RetrofitNetworkResult<MediaPictures, LocalMediaPictures>
}
