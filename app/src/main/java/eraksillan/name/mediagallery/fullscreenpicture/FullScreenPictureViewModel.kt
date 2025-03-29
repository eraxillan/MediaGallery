package eraksillan.name.mediagallery.fullscreenpicture

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel(assistedFactory = FullScreenPictureViewModel.ViewModelFactory::class)
class FullScreenPictureViewModel @AssistedInject constructor(
    @Assisted private val navController: NavController,
) : ViewModel() {

    fun onEvent(action: FullScreenPictureAction) {
        when (action) {
            is FullScreenPictureAction.NavigateBackClicked -> {
                navController.popBackStack()
            }
        }
    }

    @AssistedFactory
    interface ViewModelFactory {
        fun create(navController: NavController): FullScreenPictureViewModel
    }
}
