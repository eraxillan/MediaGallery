package eraksillan.name.mediagallery.mediacast

sealed class MediaCastAction {
    data object NavigateBackClicked : MediaCastAction()
    class CharacterClicked(val id: Int) : MediaCastAction()
}
