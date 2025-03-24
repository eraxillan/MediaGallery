package eraksillan.name.mediagallery.local.model

data class LocalMediaFilter(
    val year: Int,
    val season: LocalMedia.Season,
    val type: LocalMediaTypeFilter,
    val continuing: Boolean,
)
