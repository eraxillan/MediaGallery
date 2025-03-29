package eraksillan.name.mediagallery.mediadetail

import android.app.Activity

sealed class MediaDetailAction {
    data object NavigateBackClicked : MediaDetailAction()
    data object AddToFavoritesClicked : MediaDetailAction()
    data object RemoveFromFavoritesClicked : MediaDetailAction()
    class ShareMediaClicked(val activity: Activity) : MediaDetailAction()
    class FullScreenPictureClicked(val url: String) : MediaDetailAction()
}
