package eraksillan.name.mediagallery.local.model

import eraksillan.name.mediagallery.R

enum class LocalMediaTypeFilter(val titleResId: Int, val query: String) {
    TV_NEW(R.string.tv_new, "tv"),
    TV_CONTINUED(R.string.tv_continued, "tv"),
    ONA(R.string.ona, "ona"),
    OVA(R.string.ova, "ova"),
    MOVIE(R.string.movie, "movie"),
    SPECIAL(R.string.special, "special"),
    MUSIC(R.string.music, "music"),
    //TV_SPECIAL(R.string.tv_special, "unknown"),
}
