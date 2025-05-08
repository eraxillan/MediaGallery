package eraksillan.name.mediagallery.mediastaff

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import eraksillan.name.mediagallery.local.model.LocalMedia

@HiltViewModel(assistedFactory = MediaStaffViewModel.ViewModelFactory::class)
class MediaStaffViewModel @AssistedInject constructor(
    @Assisted private val navController: NavController,
    @Assisted private val data: List<LocalMedia.Person>,
) : ViewModel() {

    fun onEvent(action: MediaStaffAction) {
        when (action) {
            is MediaStaffAction.NavigateBackClicked -> {
                navController.popBackStack()
            }

            is MediaStaffAction.PersonClicked -> {
                TODO()
            }
        }
    }

    @AssistedFactory
    interface ViewModelFactory {
        fun create(navController: NavController, data: List<LocalMedia.Person>): MediaStaffViewModel
    }
}
