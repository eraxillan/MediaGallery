package eraksillan.name.mediagallery.remote

import eraksillan.name.mediagallery.local.model.LocalMediaCast
import eraksillan.name.mediagallery.local.model.LocalMediaExternalLinks
import eraksillan.name.mediagallery.local.model.LocalMediaMoreInfo
import eraksillan.name.mediagallery.local.model.LocalMediaPictures
import eraksillan.name.mediagallery.local.model.LocalMediaRelations
import eraksillan.name.mediagallery.local.model.LocalMediaReviews
import eraksillan.name.mediagallery.local.model.LocalMediaStaff
import eraksillan.name.mediagallery.local.model.LocalMediaThemes
import eraksillan.name.mediagallery.local.model.LocalMediaVideos
import eraksillan.name.mediagallery.local.model.LocalSchedule
import eraksillan.name.mediagallery.local.model.LocalSeasonList
import eraksillan.name.mediagallery.remote.model.MediaCast
import eraksillan.name.mediagallery.remote.model.MediaExternalLinks
import eraksillan.name.mediagallery.remote.model.MediaMoreInfo
import eraksillan.name.mediagallery.remote.model.MediaPictures
import eraksillan.name.mediagallery.remote.model.MediaRelations
import eraksillan.name.mediagallery.remote.model.MediaReviews
import eraksillan.name.mediagallery.remote.model.MediaStaff
import eraksillan.name.mediagallery.remote.model.MediaThemes
import eraksillan.name.mediagallery.remote.model.MediaVideos
import eraksillan.name.mediagallery.remote.model.Schedule
import eraksillan.name.mediagallery.remote.model.SeasonList
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

private const val BASE_URL = "https://api.jikan.moe/v4/"

@Singleton
class RetrofitNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: dagger.Lazy<Call.Factory>,
) : RetrofitMediaRepository {

    private val networkApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            // We use callFactory lambda here with dagger.Lazy<Call.Factory>
            // to prevent initializing OkHttp on the main thread
            .callFactory { okhttpCallFactory.get().newCall(it) }
            .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            .addCallAdapterFactory(RetrofitCallAdapterFactory.create())
            .build()
            .create(MyAnimeListService::class.java)

    override suspend fun getSeasonMedias(
        year: Int,
        season: String,
        page: Int,
        pageSize: Int,
        filter: String,
        continuing: Boolean
    ): RetrofitNetworkResult<Schedule, LocalSchedule> {
        return networkApi.getSeasonMedias(
            year = year,
            season = season,
            page = page,
            pageSize = pageSize,
            filter = filter,
            continuing = continuing
        )
    }

    override suspend fun getMediaPictures(
        id: Int
    ): RetrofitNetworkResult<MediaPictures, LocalMediaPictures> {
        return networkApi.getAnimePictures(id)
    }

    override suspend fun getMediaVideos(id: Int): RetrofitNetworkResult<MediaVideos, LocalMediaVideos> {
        return networkApi.getAnimeVideos(id)
    }

    override suspend fun getAnimeExternalLinks(id: Int): RetrofitNetworkResult<MediaExternalLinks, LocalMediaExternalLinks> {
        return networkApi.getAnimeExternalLinks(id)
    }

    override suspend fun getAnimeRelations(id: Int): RetrofitNetworkResult<MediaRelations, LocalMediaRelations> {
        return networkApi.getAnimeRelations(id)
    }

    override suspend fun getAnimeMoreInfo(id: Int): RetrofitNetworkResult<MediaMoreInfo, LocalMediaMoreInfo> {
        return networkApi.getAnimeMoreInfo(id)
    }

    override suspend fun getAnimeCharacters(id: Int): RetrofitNetworkResult<MediaCast, LocalMediaCast> {
        return networkApi.getAnimeCharacters(id)
    }

    override suspend fun getAnimeStaff(id: Int): RetrofitNetworkResult<MediaStaff, LocalMediaStaff> {
        return networkApi.getAnimeStaff(id)
    }

    override suspend fun getAnimeThemes(id: Int): RetrofitNetworkResult<MediaThemes, LocalMediaThemes> {
        return networkApi.getAnimeThemes(id)
    }

    override suspend fun getAnimeReviews(id: Int): RetrofitNetworkResult<MediaReviews, LocalMediaReviews> {
        return networkApi.getAnimeReviews(id)
    }

    override suspend fun getSeasonList(): RetrofitNetworkResult<SeasonList, LocalSeasonList> {
        return networkApi.getSeasonList()
    }
}
