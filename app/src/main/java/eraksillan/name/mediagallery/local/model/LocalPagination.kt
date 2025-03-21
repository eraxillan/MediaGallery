package eraksillan.name.mediagallery.local.model

data class LocalPagination(
    val hasNextPage: Boolean,
    val currentPage: Int,
    val lastPage: Int,
    val items: Items,
) {
    data class Items(
        val count: Int,
        val total: Int,
        val perPage: Int,
    )
}
