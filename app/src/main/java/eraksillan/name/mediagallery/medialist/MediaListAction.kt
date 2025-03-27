package eraksillan.name.mediagallery.medialist

sealed class MediaListAction {
    data object GetNextPage : MediaListAction()
    class MediaTypeSelected(val index: Int) : MediaListAction()
    class SortTypeClicked(val index: Int) : MediaListAction()
    data object NavigateToDetail : MediaListAction()
}
