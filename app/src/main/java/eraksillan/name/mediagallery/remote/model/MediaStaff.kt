package eraksillan.name.mediagallery.remote.model

import eraksillan.name.mediagallery.local.model.LocalMediaStaff
import eraksillan.name.mediagallery.remote.RetrofitResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaStaff(
    @SerialName("data")
    val data: List<Media.Person>
) : RetrofitResponse<LocalMediaStaff> {

    override fun toLocal(): LocalMediaStaff {
        return LocalMediaStaff(data.map { it.toLocal() })
    }
}
