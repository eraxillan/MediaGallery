package eraksillan.name.mediagallery.remote.model

import eraksillan.name.mediagallery.local.model.LocalMediaMoreInfo
import eraksillan.name.mediagallery.remote.RetrofitResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaMoreInfo(
    @SerialName("data")
    val data: MoreInfo
) : RetrofitResponse<LocalMediaMoreInfo> {

    @Serializable
    data class MoreInfo(
        @SerialName("moreinfo")
        val info: String
    )

    override fun toLocal(): LocalMediaMoreInfo {
        return LocalMediaMoreInfo(data.info)
    }
}
