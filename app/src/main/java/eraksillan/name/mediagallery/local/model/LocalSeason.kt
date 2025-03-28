package eraksillan.name.mediagallery.local.model

import java.util.UUID

data class LocalSeason(
    val uniqueId: String = UUID.randomUUID().toString(),
    val year: Int,
    val seasons: List<String>,
)
