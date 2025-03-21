package eraksillan.name.mediagallery.medialist

import androidx.navigation.NavController
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import eraksillan.name.mediagallery.ComposeScreen
import eraksillan.name.mediagallery.architecture.NetworkResult
import eraksillan.name.mediagallery.local.model.LocalMedia
import eraksillan.name.mediagallery.paging.PagingMediaRepository
import eraksillan.name.mediagallery.paging.PagingViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// https://medium.com/@cgaisl/how-to-pass-arguments-to-a-hiltviewmodel-from-compose-97c74a75f772
@HiltViewModel(assistedFactory = MediaListViewModel.ViewModelFactory::class)
class MediaListViewModel @AssistedInject constructor(
    @Assisted private val navController: NavController,
    private val repository: PagingMediaRepository
) : PagingViewModel<LocalMedia>() {

    override var callback: (Int, Int) -> Flow<NetworkResult<List<LocalMedia>>> = { page: Int, pageSize: Int ->
        repository.getWeeklySchedules(page, pageSize)
            .map {
                when (it) {
                    is NetworkResult.Success -> NetworkResult.Success(it.data.mediaList)
                    is NetworkResult.Error -> NetworkResult.Error(it.code, it.message)
                    is NetworkResult.Exception -> NetworkResult.Exception(it.e)
                }
            }
    }

    @AssistedFactory
    interface ViewModelFactory {
        fun create(navController: NavController): MediaListViewModel
    }

    fun navigateToDetail() {
        navController.navigate(ComposeScreen.MEDIA_DETAIL.name)
    }

    init {
        getPageData()
    }
}
