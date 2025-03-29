package eraksillan.name.mediagallery.mediadetail

sealed class MediaDetailAction {
    data object NavigateBackClicked : MediaDetailAction()
    data object AddToFavoritesClicked : MediaDetailAction()
    data object RemoveFromFavoritesClicked : MediaDetailAction()
    data object ShareMediaClicked : MediaDetailAction()
    class FullScreenPictureClicked(val url: String) : MediaDetailAction()
}
