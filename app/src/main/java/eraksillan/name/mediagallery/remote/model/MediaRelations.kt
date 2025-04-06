package eraksillan.name.mediagallery.remote.model

import eraksillan.name.mediagallery.local.model.LocalMediaRelations
import eraksillan.name.mediagallery.remote.RetrofitResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaRelations(
    @SerialName("data")
    val data: List<Media.Relation>
) : RetrofitResponse<LocalMediaRelations> {

    override fun toLocal(): LocalMediaRelations {
        return LocalMediaRelations(data.map { it.toLocal() })
    }
}
