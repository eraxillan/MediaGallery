package eraksillan.name.mediagallery.medianews

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import eraksillan.name.mediagallery.local.model.LocalMedia

@HiltViewModel(assistedFactory = MediaNewsViewModel.ViewModelFactory::class)
class MediaNewsViewModel @AssistedInject constructor(
    @Assisted private val navController: NavController,
    @Assisted private val data: List<LocalMedia.NewsItem>,
) : ViewModel() {

    fun onEvent(action: MediaNewsAction) {
        when (action) {
            is MediaNewsAction.NavigateBackClicked -> {
                navController.popBackStack()
            }

            is MediaNewsAction.NewsItemClicked -> {
                TODO()
            }
        }
    }

    @AssistedFactory
    interface ViewModelFactory {
        fun create(navController: NavController, data: List<LocalMedia.NewsItem>): MediaNewsViewModel
    }
}
