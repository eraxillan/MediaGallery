package eraksillan.name.mediagallery.mediadetail

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

// https://medium.com/@cgaisl/how-to-pass-arguments-to-a-hiltviewmodel-from-compose-97c74a75f772
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
