package eraksillan.name.mediagallery.local.model

data class LocalMediaVideos(
    val data: Data
) {
    data class Data(
        val promos: List<Trailer>,
        val episodes: List<Episode>,
    )

    data class Trailer(
        val title: String,
        val trailer: LocalMedia.Trailer
    )

    data class Episode(
        val malId: Int,
        val title: String,
        val episode: String,
        val url: String,
        val images: LocalMedia.ImageUrls?
    )
}
