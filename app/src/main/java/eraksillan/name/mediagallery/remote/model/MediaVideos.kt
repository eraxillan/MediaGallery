package eraksillan.name.mediagallery.remote.model

import eraksillan.name.mediagallery.local.model.LocalMediaVideos
import eraksillan.name.mediagallery.remote.RetrofitResponse
import eraksillan.name.mediagallery.remote.model.Media.ImageUrls
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaVideos(
    @SerialName("data")
    val data: Data
) : RetrofitResponse<LocalMediaVideos> {

    override fun toLocal(): LocalMediaVideos {
        return LocalMediaVideos(
            LocalMediaVideos.Data(
                promos = data.promos.map { LocalMediaVideos.Trailer(it.title, it.trailer.toLocal()) },
                episodes = data.episodes.map { LocalMediaVideos.Episode(it.malId, it.title, it.episode, it.url, it.images?.toLocal()) }
            )
        )
    }

    @Serializable
    data class Data(
        @SerialName("promo")
        val promos: List<Trailer>,
        @SerialName("episodes")
        val episodes: List<Episode>,
    )

    @Serializable
    data class Trailer(
        @SerialName("title")
        val title: String,
        @SerialName("trailer")
        val trailer: Media.Trailer
    )

    @Serializable
    data class Episode(
        @SerialName("mal_id")
        val malId: Int,
        @SerialName("title")
        val title: String,
        @SerialName("episode")
        val episode: String,
        @SerialName("url")
        val url: String,
        @SerialName("images")
        val images: ImageUrls?
    )
}
