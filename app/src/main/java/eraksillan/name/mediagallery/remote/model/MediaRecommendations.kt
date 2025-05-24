package eraksillan.name.mediagallery.remote.model

import eraksillan.name.mediagallery.local.model.LocalMediaRecommendations
import eraksillan.name.mediagallery.remote.RetrofitResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaRecommendations(
    @SerialName("data")
    val data: List<Media.Recommendation>
) : RetrofitResponse<LocalMediaRecommendations> {

    override fun toLocal(): LocalMediaRecommendations {
        return LocalMediaRecommendations(data.map { it.toLocal() })
    }
}
