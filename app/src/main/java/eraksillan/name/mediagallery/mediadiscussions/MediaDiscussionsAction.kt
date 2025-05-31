package eraksillan.name.mediagallery.mediadiscussions

sealed class MediaDiscussionsAction {
    data object NavigateBackClicked : MediaDiscussionsAction()
    class DiscussionClicked(val id: Int) : MediaDiscussionsAction()
}
