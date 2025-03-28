package eraksillan.name.mediagallery.medialist

import eraksillan.name.mediagallery.local.model.LocalMedia

sealed class MediaListAction {
    data object GetNextPage : MediaListAction()
    class MediaTypeSelected(val index: Int) : MediaListAction()
    class SortTypeClicked(val index: Int) : MediaListAction()
    class NavigateToDetail(val data: LocalMedia) : MediaListAction()
}
