package eraksillan.name.mediagallery.remote.model

import eraksillan.name.mediagallery.local.model.LocalMediaThemes
import eraksillan.name.mediagallery.remote.RetrofitResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaThemes(
    @SerialName("data")
    val data: Media.Themes
) : RetrofitResponse<LocalMediaThemes> {

    override fun toLocal(): LocalMediaThemes {
        return LocalMediaThemes(data.toLocal())
    }
}
