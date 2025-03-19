package eraksillan.name.mediagallery.mediadetail

import eraksillan.name.mediagallery.ErrorState
import eraksillan.name.mediagallery.LoadingState

data class MediaDetailState(
    val data: String,
    val loadingState: LoadingState = LoadingState.NONE,
    val errorState: ErrorState = ErrorState.NONE,
) {
    fun isLoading() = loadingState == LoadingState.LOADING

    //fun isError() = password.isEmpty() && needValidate
}
