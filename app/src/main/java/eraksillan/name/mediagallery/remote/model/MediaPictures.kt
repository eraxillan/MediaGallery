package eraksillan.name.mediagallery.remote.model

import eraksillan.name.mediagallery.local.model.LocalMediaPictures
import eraksillan.name.mediagallery.remote.RetrofitResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaPictures(
    @SerialName("data")
    val data: List<Media.Images>
) : RetrofitResponse<LocalMediaPictures> {

    override fun toLocal(): LocalMediaPictures {
        return LocalMediaPictures(data.map { it.toLocal() })
    }
}
