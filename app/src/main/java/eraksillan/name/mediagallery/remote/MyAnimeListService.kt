package eraksillan.name.mediagallery.remote

import com.lembergsolutions.retrofitretry.api.RetryHandler
import com.lembergsolutions.retrofitretry.api.RetryOnError
import eraksillan.name.mediagallery.local.model.LocalMediaCast
import eraksillan.name.mediagallery.local.model.LocalMediaExternalLinks
import eraksillan.name.mediagallery.local.model.LocalMediaMoreInfo
import eraksillan.name.mediagallery.local.model.LocalMediaPictures
import eraksillan.name.mediagallery.local.model.LocalMediaRelations
import eraksillan.name.mediagallery.local.model.LocalMediaVideos
import eraksillan.name.mediagallery.local.model.LocalSchedule
import eraksillan.name.mediagallery.local.model.LocalSeasonList
import eraksillan.name.mediagallery.remote.model.MediaCast
import eraksillan.name.mediagallery.remote.model.MediaExternalLinks
import eraksillan.name.mediagallery.remote.model.MediaMoreInfo
import eraksillan.name.mediagallery.remote.model.MediaPictures
import eraksillan.name.mediagallery.remote.model.MediaRelations
import eraksillan.name.mediagallery.remote.model.MediaVideos
import eraksillan.name.mediagallery.remote.model.Schedule
import eraksillan.name.mediagallery.remote.model.SeasonList
import okhttp3.Request
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class MyCustomRetryHandler : RetryHandler {
    override fun getRetryDelay(request: Request, result: Result<Response<out Any?>>, retryCount: Int, maxRetries: Int): Long {
        return 1_000
    }
}

interface MyAnimeListService {
    // https://api.jikan.moe/v4/seasons/{year}/{season}
    @RetryOnError(10, handlerClass = MyCustomRetryHandler::class)
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
    @RetryOnError(10, handlerClass = MyCustomRetryHandler::class)
    @GET("anime/{id}/pictures")
    suspend fun getAnimePictures(
        @Path("id") id: Int
    ): RetrofitNetworkResult<MediaPictures, LocalMediaPictures>

    // https://api.jikan.moe/v4/anime/{id}/videos
    @RetryOnError(10, handlerClass = MyCustomRetryHandler::class)
    @GET("anime/{id}/videos")
    suspend fun getAnimeVideos(
        @Path("id") id: Int
    ) : RetrofitNetworkResult<MediaVideos, LocalMediaVideos>

    // https://api.jikan.moe/v4/anime/{id}/external
    @RetryOnError(10, handlerClass = MyCustomRetryHandler::class)
    @GET("anime/{id}/external")
    suspend fun getAnimeExternalLinks(
        @Path("id") id: Int
    ) : RetrofitNetworkResult<MediaExternalLinks, LocalMediaExternalLinks>

    // https://api.jikan.moe/v4/anime/{id}/relations
    @RetryOnError(10, handlerClass = MyCustomRetryHandler::class)
    @GET("anime/{id}/relations")
    suspend fun getAnimeRelations(
        @Path("id") id: Int
    ) : RetrofitNetworkResult<MediaRelations, LocalMediaRelations>

    // https://api.jikan.moe/v4/anime/{id}/moreinfo
    @RetryOnError(10, handlerClass = MyCustomRetryHandler::class)
    @GET("anime/{id}/moreinfo")
    suspend fun getAnimeMoreInfo(
        @Path("id") id: Int
    ) : RetrofitNetworkResult<MediaMoreInfo, LocalMediaMoreInfo>

    // https://api.jikan.moe/v4/anime/{id}/characters
    @RetryOnError
    @GET("anime/{id}/characters")
    suspend fun getAnimeCharacters(
        @Path("id") id: Int
    ) : RetrofitNetworkResult<MediaCast, LocalMediaCast>

    // https://api.jikan.moe/v4/seasons
    @RetryOnError(10, handlerClass = MyCustomRetryHandler::class)
    @GET("seasons")
    suspend fun getSeasonList(): RetrofitNetworkResult<SeasonList, LocalSeasonList>
}
