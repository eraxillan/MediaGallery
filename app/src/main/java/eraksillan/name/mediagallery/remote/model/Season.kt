package eraksillan.name.mediagallery.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Season(
    @SerialName("year")
    val year: Int,
    @SerialName("seasons")
    val seasons: List<String>,
)
