package eraksillan.name.mediagallery.mediadetail

import android.app.Activity
import android.content.Context
import eraksillan.name.mediagallery.local.model.LocalMedia

sealed class MediaDetailAction {
    data object NavigateBackClicked : MediaDetailAction()
    data object AddToFavoritesClicked : MediaDetailAction()
    data object RemoveFromFavoritesClicked : MediaDetailAction()
    class ShareMediaClicked(val activity: Activity) : MediaDetailAction()
    class FullScreenPictureClicked(val url: String) : MediaDetailAction()
    class GenreClicked(val id: Int) : MediaDetailAction()
    class VideoPreviewClicked(val context: Context, val id: String) : MediaDetailAction()
    data object MoreInformationClicked : MediaDetailAction()
    class ProducerClicked(val index: Int) : MediaDetailAction()
    class ExternalLinkClicked(val index: Int) : MediaDetailAction()
    class RelationClicked(val malId: Int) : MediaDetailAction()
    class CharacterClicked(val malId: Int) : MediaDetailAction()
    class VoiceActorClicked(val malId: Int) : MediaDetailAction()
    class MoreCastClicked(val data: List<LocalMedia.Cast>) : MediaDetailAction()
    class PersonClicked(val malId: Int) : MediaDetailAction()
    class MoreStaffClicked(val data: List<LocalMedia.Person>) : MediaDetailAction()
}
