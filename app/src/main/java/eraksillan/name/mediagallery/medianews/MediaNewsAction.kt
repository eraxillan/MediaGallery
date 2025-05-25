package eraksillan.name.mediagallery.medianews

sealed class MediaNewsAction {
    data object NavigateBackClicked : MediaNewsAction()
    class NewsItemClicked(val id: Int) : MediaNewsAction()
}
