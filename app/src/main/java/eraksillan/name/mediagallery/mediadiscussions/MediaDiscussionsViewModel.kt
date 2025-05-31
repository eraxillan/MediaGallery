package eraksillan.name.mediagallery.mediadiscussions

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import eraksillan.name.mediagallery.local.model.LocalMedia

@HiltViewModel(assistedFactory = MediaDiscussionsViewModel.ViewModelFactory::class)
class MediaDiscussionsViewModel @AssistedInject constructor(
    @Assisted private val navController: NavController,
    @Assisted private val data: List<LocalMedia.Discussion>,
) : ViewModel() {

    fun onEvent(action: MediaDiscussionsAction) {
        when (action) {
            is MediaDiscussionsAction.NavigateBackClicked -> {
                navController.popBackStack()
            }

            is MediaDiscussionsAction.DiscussionClicked -> {
                TODO()
            }
        }
    }

    @AssistedFactory
    interface ViewModelFactory {
        fun create(navController: NavController, data: List<LocalMedia.Discussion>): MediaDiscussionsViewModel
    }
}
