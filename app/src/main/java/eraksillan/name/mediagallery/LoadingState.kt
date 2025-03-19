package eraksillan.name.mediagallery

enum class LoadingState {
    NONE,
    LOADING,
    REFRESHING,
    LOADING_SKELETON,
    LOADING_NEXT_PAGE
}

fun LoadingState.isLoading() = this == LoadingState.LOADING
