package eraksillan.name.mediagallery.remote

import eraksillan.name.mediagallery.local.model.LocalMediaPictures
import eraksillan.name.mediagallery.local.model.LocalSchedule
import eraksillan.name.mediagallery.remote.model.MediaPictures
import eraksillan.name.mediagallery.remote.model.Schedule
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

    override suspend fun getWeeklySchedules(
        page: Int,
        pageSize: Int
    ): RetrofitNetworkResult<Schedule, LocalSchedule> {
        return networkApi.getWeeklySchedules(page, pageSize)
    }

    override suspend fun getMediaPictures(
        id: Int
    ): RetrofitNetworkResult<MediaPictures, LocalMediaPictures> {
        return networkApi.getAnimePictures(id)
    }
}
