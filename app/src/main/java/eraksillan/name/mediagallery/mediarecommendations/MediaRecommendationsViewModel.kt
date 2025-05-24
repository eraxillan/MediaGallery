package eraksillan.name.mediagallery.mediarecommendations

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import eraksillan.name.mediagallery.local.model.LocalMedia

@HiltViewModel(assistedFactory = MediaRecommendationsViewModel.ViewModelFactory::class)
class MediaRecommendationsViewModel @AssistedInject constructor(
    @Assisted private val navController: NavController,
    @Assisted private val data: List<LocalMedia.Recommendation>,
) : ViewModel() {

    fun onEvent(action: MediaRecommendationsAction) {
        when (action) {
            is MediaRecommendationsAction.NavigateBackClicked -> {
                navController.popBackStack()
            }

            is MediaRecommendationsAction.RecommendationClicked -> {
                TODO()
            }
        }
    }

    @AssistedFactory
    interface ViewModelFactory {
        fun create(navController: NavController, data: List<LocalMedia.Recommendation>): MediaRecommendationsViewModel
    }
}
