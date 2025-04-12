package eraksillan.name.mediagallery.remote.model

import eraksillan.name.mediagallery.local.model.LocalMediaCast
import eraksillan.name.mediagallery.remote.RetrofitResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaCast(
    @SerialName("data")
    val data: List<Media.Cast>
) : RetrofitResponse<LocalMediaCast> {

    override fun toLocal(): LocalMediaCast {
        return LocalMediaCast(data.map { it.toLocal() })
    }
}
