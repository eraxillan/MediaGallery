package eraksillan.name.mediagallery.local.model

import eraksillan.name.mediagallery.R

enum class LocalMediaSortType(val title: Int) {
    BY_MEMBER_COUNT(R.string.sort_by_members),
    BY_START_DATE(R.string.sort_by_start_date),
    BY_SCORE(R.string.sort_by_score),
}
