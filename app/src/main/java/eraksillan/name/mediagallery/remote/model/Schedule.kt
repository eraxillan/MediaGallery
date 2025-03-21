package eraksillan.name.mediagallery.remote.model

import eraksillan.name.mediagallery.local.model.LocalSchedule
import eraksillan.name.mediagallery.remote.RetrofitResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Schedule(
    @SerialName("pagination")
    val pagination: Pagination,
    @SerialName("data")
    val data: List<Media>,
) : RetrofitResponse<LocalSchedule> {

    override fun toLocal(): LocalSchedule {
        return LocalSchedule(pagination.toLocal(), data.map { it.toLocal() })
    }
}
