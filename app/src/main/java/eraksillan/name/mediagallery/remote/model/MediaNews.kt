package eraksillan.name.mediagallery.remote.model

import eraksillan.name.mediagallery.local.model.LocalMediaNews
import eraksillan.name.mediagallery.remote.RetrofitResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaNews(
    @SerialName("data")
    val data: List<Media.NewsItem>
) : RetrofitResponse<LocalMediaNews> {

    override fun toLocal(): LocalMediaNews {
        return LocalMediaNews(data.map { it.toLocal() })
    }
}
