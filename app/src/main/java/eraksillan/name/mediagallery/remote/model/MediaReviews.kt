package eraksillan.name.mediagallery.remote.model

import eraksillan.name.mediagallery.local.model.LocalMediaReviews
import eraksillan.name.mediagallery.remote.RetrofitResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaReviews(
    @SerialName("data")
    val data: List<Media.Review>
) : RetrofitResponse<LocalMediaReviews> {

    override fun toLocal(): LocalMediaReviews {
        return LocalMediaReviews(data.map { it.toLocal() })
    }
}
