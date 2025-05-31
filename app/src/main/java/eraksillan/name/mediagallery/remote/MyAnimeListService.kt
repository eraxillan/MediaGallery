package eraksillan.name.mediagallery.remote

import eraksillan.name.mediagallery.local.model.LocalMediaCast
import eraksillan.name.mediagallery.local.model.LocalMediaDiscussions
import eraksillan.name.mediagallery.local.model.LocalMediaExternalLinks
import eraksillan.name.mediagallery.local.model.LocalMediaMoreInfo
import eraksillan.name.mediagallery.local.model.LocalMediaNews
import eraksillan.name.mediagallery.local.model.LocalMediaPictures
import eraksillan.name.mediagallery.local.model.LocalMediaRecommendations
import eraksillan.name.mediagallery.local.model.LocalMediaRelations
import eraksillan.name.mediagallery.local.model.LocalMediaReviews
import eraksillan.name.mediagallery.local.model.LocalMediaStaff
import eraksillan.name.mediagallery.local.model.LocalMediaThemes
import eraksillan.name.mediagallery.local.model.LocalMediaVideos
import eraksillan.name.mediagallery.local.model.LocalSchedule
import eraksillan.name.mediagallery.local.model.LocalSeasonList
import eraksillan.name.mediagallery.remote.model.MediaCast
import eraksillan.name.mediagallery.remote.model.MediaDiscussions
import eraksillan.name.mediagallery.remote.model.MediaExternalLinks
import eraksillan.name.mediagallery.remote.model.MediaMoreInfo
import eraksillan.name.mediagallery.remote.model.MediaNews
import eraksillan.name.mediagallery.remote.model.MediaPictures
import eraksillan.name.mediagallery.remote.model.MediaRecommendations
import eraksillan.name.mediagallery.remote.model.MediaRelations
import eraksillan.name.mediagallery.remote.model.MediaReviews
import eraksillan.name.mediagallery.remote.model.MediaStaff
import eraksillan.name.mediagallery.remote.model.MediaThemes
import eraksillan.name.mediagallery.remote.model.MediaVideos
import eraksillan.name.mediagallery.remote.model.Schedule
import eraksillan.name.mediagallery.remote.model.SeasonList
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

    // https://api.jikan.moe/v4/anime/{id}/videos
    @GET("anime/{id}/videos")
    suspend fun getAnimeVideos(
        @Path("id") id: Int
    ): RetrofitNetworkResult<MediaVideos, LocalMediaVideos>

    // https://api.jikan.moe/v4/anime/{id}/external
    @GET("anime/{id}/external")
    suspend fun getAnimeExternalLinks(
        @Path("id") id: Int
    ): RetrofitNetworkResult<MediaExternalLinks, LocalMediaExternalLinks>

    // https://api.jikan.moe/v4/anime/{id}/relations
    @GET("anime/{id}/relations")
    suspend fun getAnimeRelations(
        @Path("id") id: Int
    ): RetrofitNetworkResult<MediaRelations, LocalMediaRelations>

    // https://api.jikan.moe/v4/anime/{id}/moreinfo
    @GET("anime/{id}/moreinfo")
    suspend fun getAnimeMoreInfo(
        @Path("id") id: Int
    ): RetrofitNetworkResult<MediaMoreInfo, LocalMediaMoreInfo>

    // https://api.jikan.moe/v4/anime/{id}/characters
    @GET("anime/{id}/characters")
    suspend fun getAnimeCharacters(
        @Path("id") id: Int
    ): RetrofitNetworkResult<MediaCast, LocalMediaCast>

    // https://api.jikan.moe/v4/anime/{id}/staff
    @GET("anime/{id}/staff")
    suspend fun getAnimeStaff(
        @Path("id") id: Int
    ): RetrofitNetworkResult<MediaStaff, LocalMediaStaff>

    // https://api.jikan.moe/v4/anime/{id}/themes
    @GET("anime/{id}/themes")
    suspend fun getAnimeThemes(
        @Path("id") id: Int
    ) : RetrofitNetworkResult<MediaThemes, LocalMediaThemes>

    // https://api.jikan.moe/v4/anime/{id}/reviews
    @GET("anime/{id}/reviews?preliminary=true&spoilers=true")
    suspend fun getAnimeReviews(
        @Path("id") id: Int
    ) : RetrofitNetworkResult<MediaReviews, LocalMediaReviews>

    // https://api.jikan.moe/v4/anime/{id}/recommendations
    @GET("anime/{id}/recommendations")
    suspend fun getAnimeRecommendations(
        @Path("id") id: Int
    ) : RetrofitNetworkResult<MediaRecommendations, LocalMediaRecommendations>

    // https://api.jikan.moe/v4/anime/{id}/news
    @GET("anime/{id}/news")
    suspend fun getAnimeNews(
        @Path("id") id: Int
    ) : RetrofitNetworkResult<MediaNews, LocalMediaNews>

    // https://api.jikan.moe/v4/anime/{id}/forum
    @GET("anime/{id}/forum")
    suspend fun getAnimeDiscussions(
        @Path("id") id: Int
    ) : RetrofitNetworkResult<MediaDiscussions, LocalMediaDiscussions>

    // https://api.jikan.moe/v4/seasons
    @GET("seasons")
    suspend fun getSeasonList(): RetrofitNetworkResult<SeasonList, LocalSeasonList>
}
