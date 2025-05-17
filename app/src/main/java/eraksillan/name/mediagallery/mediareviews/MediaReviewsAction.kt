package eraksillan.name.mediagallery.mediareviews

sealed class MediaReviewsAction {
    data object NavigateBackClicked : MediaReviewsAction()
    class ReviewClicked(val id: Int) : MediaReviewsAction()
}
