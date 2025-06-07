package eraksillan.name.mediagallery.remote.model

import eraksillan.name.mediagallery.local.model.LocalMediaStatistics
import eraksillan.name.mediagallery.remote.RetrofitResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaStatistics(
    @SerialName("data")
    val data: Media.Statistics
) : RetrofitResponse<LocalMediaStatistics> {

    override fun toLocal(): LocalMediaStatistics {
        return LocalMediaStatistics(data.toLocal())
    }
}
