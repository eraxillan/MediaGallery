package eraksillan.name.mediagallery.remote.model

import eraksillan.name.mediagallery.local.model.LocalSeason
import eraksillan.name.mediagallery.local.model.LocalSeasonList
import eraksillan.name.mediagallery.remote.RetrofitResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeasonList(
    @SerialName("data")
    val data: List<Season>,
) : RetrofitResponse<LocalSeasonList> {

    override fun toLocal(): LocalSeasonList {
        return LocalSeasonList(data.map { LocalSeason(year = it.year, seasons = it.seasons) })
    }
}
