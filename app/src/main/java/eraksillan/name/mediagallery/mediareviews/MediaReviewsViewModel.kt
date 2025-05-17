package eraksillan.name.mediagallery.mediareviews

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import eraksillan.name.mediagallery.local.model.LocalMedia

@HiltViewModel(assistedFactory = MediaReviewsViewModel.ViewModelFactory::class)
class MediaReviewsViewModel @AssistedInject constructor(
    @Assisted private val navController: NavController,
    @Assisted private val data: List<LocalMedia.Review>,
) : ViewModel() {

    fun onEvent(action: MediaReviewsAction) {
        when (action) {
            is MediaReviewsAction.NavigateBackClicked -> {
                navController.popBackStack()
            }

            is MediaReviewsAction.ReviewClicked -> {
                TODO()
            }
        }
    }

    @AssistedFactory
    interface ViewModelFactory {
        fun create(navController: NavController, data: List<LocalMedia.Review>): MediaReviewsViewModel
    }
}
