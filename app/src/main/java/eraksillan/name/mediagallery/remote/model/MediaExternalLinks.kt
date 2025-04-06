package eraksillan.name.mediagallery.remote.model

import eraksillan.name.mediagallery.local.model.LocalMediaExternalLinks
import eraksillan.name.mediagallery.remote.RetrofitResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaExternalLinks(
    @SerialName("data")
    val data: List<Media.Link>
) : RetrofitResponse<LocalMediaExternalLinks> {

    override fun toLocal(): LocalMediaExternalLinks {
        return LocalMediaExternalLinks(data.map { it.toLocal() })
    }
}
