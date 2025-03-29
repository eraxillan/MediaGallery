package eraksillan.name.mediagallery.mediadetail

import eraksillan.name.mediagallery.architecture.ErrorState
import eraksillan.name.mediagallery.architecture.LoadingState
import eraksillan.name.mediagallery.local.model.LocalMedia

data class MediaDetailState(
    val data: LocalMedia,
    val loadingState: LoadingState = LoadingState.NONE,
    val errorState: ErrorState = ErrorState.NONE,
) {
    fun isLoading() = loadingState == LoadingState.LOADING

    //fun isError() = password.isEmpty() && needValidate

    companion object {
        fun getDefault(data: LocalMedia): MediaDetailState {
            return MediaDetailState(data)
        }
    }
}
