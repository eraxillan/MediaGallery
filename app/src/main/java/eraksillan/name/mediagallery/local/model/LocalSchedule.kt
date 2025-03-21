package eraksillan.name.mediagallery.local.model

data class LocalSchedule(
    val pagination: LocalPagination,
    val mediaList: List<LocalMedia>,
)
