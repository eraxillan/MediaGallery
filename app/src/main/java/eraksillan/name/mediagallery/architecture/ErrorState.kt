package eraksillan.name.mediagallery.architecture

enum class ErrorState {
    NONE,
    NETWORK_ERROR,
    SERVER_ERROR,
    NETWORK_ERROR_SNACK,
    SERVER_ERROR_SNACK
}

fun ErrorState.isFullScreenError() = this == ErrorState.NETWORK_ERROR || this == ErrorState.SERVER_ERROR
