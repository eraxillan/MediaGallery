package eraksillan.name.mediagallery.mediadetail

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel(assistedFactory = MediaDetailViewModel.ViewModelFactory::class)
class MediaDetailViewModel @AssistedInject constructor(
    //val exampleRepository: ExampleRepository,
    @Assisted private val navController: NavController
) : ViewModel() {

    @AssistedFactory
    interface ViewModelFactory {
        fun create(navController: NavController): MediaDetailViewModel
    }
}
