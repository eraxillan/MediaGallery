package eraksillan.name.mediagallery.mediarecommendations

sealed class MediaRecommendationsAction {
    data object NavigateBackClicked : MediaRecommendationsAction()
    class RecommendationClicked(val id: Int) : MediaRecommendationsAction()
}
