package eraksillan.name.mediagallery.medialist

sealed class MediaListAction {
    class MediaTypeSelected(val index: Int) : MediaListAction()
    class SortTypeClicked(val index: Int) : MediaListAction()
}
