package eraksillan.name.mediagallery.remote.model

import eraksillan.name.mediagallery.local.model.LocalPagination
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pagination(
    @SerialName("has_next_page")
    val hasNextPage: Boolean,
    @SerialName("current_page")
    val currentPage: Int,
    @SerialName("last_visible_page")
    val lastPage: Int,
    @SerialName("items")
    val items: Items,
) {
    @Serializable
    data class Items(
        @SerialName("count")
        val count: Int,
        @SerialName("total")
        val total: Int,
        @SerialName("per_page")
        val perPage: Int,
    )
}

fun Pagination.Items.toLocal(): LocalPagination.Items {
    return LocalPagination.Items(count, total, perPage)
}

fun Pagination.toLocal(): LocalPagination {
    return LocalPagination(hasNextPage, currentPage, lastPage, items.toLocal())
}
