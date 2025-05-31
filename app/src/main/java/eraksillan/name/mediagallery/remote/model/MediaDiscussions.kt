package eraksillan.name.mediagallery.remote.model

import eraksillan.name.mediagallery.local.model.LocalMediaDiscussions
import eraksillan.name.mediagallery.remote.RetrofitResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaDiscussions(
    @SerialName("data")
    val data: List<Media.Discussion>
) : RetrofitResponse<LocalMediaDiscussions> {

    override fun toLocal(): LocalMediaDiscussions {
        return LocalMediaDiscussions(data.map { it.toLocal() })
    }
}
