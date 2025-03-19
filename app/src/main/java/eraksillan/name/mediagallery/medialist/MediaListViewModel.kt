package eraksillan.name.mediagallery.medialist

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import eraksillan.name.mediagallery.ComposeScreen

@HiltViewModel(assistedFactory = MediaListViewModel.ViewModelFactory::class)
class MediaListViewModel @AssistedInject constructor(
    //val exampleRepository: ExampleRepository,
    @Assisted private val navController: NavController
) : ViewModel() {

    @AssistedFactory
    interface ViewModelFactory {
        fun create(navController: NavController): MediaListViewModel
    }

    fun navigateToDetail() {
        navController.navigate(ComposeScreen.MEDIA_DETAIL.name)
    }
}
