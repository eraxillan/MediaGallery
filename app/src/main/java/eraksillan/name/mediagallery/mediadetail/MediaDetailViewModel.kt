package eraksillan.name.mediagallery.mediadetail

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import eraksillan.name.mediagallery.architecture.NetworkResult
import eraksillan.name.mediagallery.local.model.LocalMedia
import eraksillan.name.mediagallery.local.utils.createShareIntent
import eraksillan.name.mediagallery.navigation.Route
import eraksillan.name.mediagallery.paging.PagingMediaRepository
import eraksillan.name.mediagallery.paging.PagingViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

// https://medium.com/@cgaisl/how-to-pass-arguments-to-a-hiltviewmodel-from-compose-97c74a75f772
@HiltViewModel(assistedFactory = MediaDetailViewModel.ViewModelFactory::class)
class MediaDetailViewModel @AssistedInject constructor(
    @Assisted private val navController: NavController,
    @Assisted private val data: LocalMedia,
    private val repository: PagingMediaRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(MediaDetailState.getDefault(data))
    val state = _state.asStateFlow()

    val imagesPagingVM = PagingViewModel<LocalMedia.Images>(
        getPageCallback = { page: Int, pageSize: Int ->
            repository.getMediaPictures(data.malId)
                .map {
                    when (it) {
                        is NetworkResult.Success -> NetworkResult.Success(it.data.data)
                        is NetworkResult.Error -> NetworkResult.Error(it.code, it.message)
                        is NetworkResult.Exception -> NetworkResult.Exception(it.e)
                    }
                }
        }
    )

    init {
        imagesPagingVM.getPageData()
    }

    fun onEvent(action: MediaDetailAction) {
        when (action) {
            is MediaDetailAction.NavigateBackClicked -> {
                navController.popBackStack()
            }

            is MediaDetailAction.AddToFavoritesClicked -> {
                TODO()
            }

            is MediaDetailAction.RemoveFromFavoritesClicked -> {
                TODO()
            }

            is MediaDetailAction.ShareMediaClicked -> {
                val url = "https://myanimelist.net/anime/${data.malId}"
                createShareIntent(action.activity, url)
            }

            is MediaDetailAction.FullScreenPictureClicked -> {
                navController.navigate(Route.FullScreenPicture(action.url))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        imagesPagingVM.reset()
    }

    @AssistedFactory
    interface ViewModelFactory {
        fun create(navController: NavController, data: LocalMedia): MediaDetailViewModel
    }
}
