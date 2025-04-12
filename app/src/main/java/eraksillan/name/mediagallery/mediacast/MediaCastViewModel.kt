package eraksillan.name.mediagallery.mediacast

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import eraksillan.name.mediagallery.local.model.LocalMedia

@HiltViewModel(assistedFactory = MediaCastViewModel.ViewModelFactory::class)
class MediaCastViewModel @AssistedInject constructor(
    @Assisted private val navController: NavController,
    @Assisted private val data: List<LocalMedia.Cast>,
) : ViewModel() {

    fun onEvent(action: MediaCastAction) {
        when (action) {
            is MediaCastAction.NavigateBackClicked -> {
                navController.popBackStack()
            }

            is MediaCastAction.CharacterClicked -> {
                TODO()
            }
        }
    }

    @AssistedFactory
    interface ViewModelFactory {
        fun create(navController: NavController, data: List<LocalMedia.Cast>): MediaCastViewModel
    }
}
