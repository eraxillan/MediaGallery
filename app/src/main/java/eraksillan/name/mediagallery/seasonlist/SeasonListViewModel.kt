package eraksillan.name.mediagallery.seasonlist

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import eraksillan.name.mediagallery.architecture.NetworkResult
import eraksillan.name.mediagallery.local.model.LocalMedia
import eraksillan.name.mediagallery.local.model.LocalSeason
import eraksillan.name.mediagallery.navigation.Route
import eraksillan.name.mediagallery.paging.PagingMediaRepository
import eraksillan.name.mediagallery.paging.PagingViewModel
import kotlinx.coroutines.flow.map

@HiltViewModel(assistedFactory = SeasonListViewModel.ViewModelFactory::class)
class SeasonListViewModel @AssistedInject constructor(
    @Assisted private val navController: NavController,
    private val repository: PagingMediaRepository
) : ViewModel() {

    val seasonPagingVM = PagingViewModel<LocalSeason>(
        getPageCallback = { page: Int, pageSize: Int ->
            repository.getSeasonList().map {
                when (it) {
                    is NetworkResult.Success -> NetworkResult.Success(it.data.data)
                    is NetworkResult.Error -> NetworkResult.Error(it.code, it.message)
                    is NetworkResult.Exception -> NetworkResult.Exception(it.e)
                }
            }
        }
    )

    init {
        seasonPagingVM.getPageData()
    }

    fun navigateToSeason(year: Int, season: LocalMedia.Season) {
        navController.navigate(Route.MediaSeason(year, season))
    }

    fun onEvent(action: SeasonListAction) {
        when (action) {
            is SeasonListAction.NavigateToSeason -> {
                navigateToSeason(action.year, action.season)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        seasonPagingVM.reset()
    }

    @AssistedFactory
    interface ViewModelFactory {
        fun create(navController: NavController): SeasonListViewModel
    }
}
