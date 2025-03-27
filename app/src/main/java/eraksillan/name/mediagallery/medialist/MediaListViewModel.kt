package eraksillan.name.mediagallery.medialist

import androidx.navigation.NavController
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import eraksillan.name.mediagallery.ComposeScreen
import eraksillan.name.mediagallery.architecture.NetworkResult
import eraksillan.name.mediagallery.local.model.LocalMedia
import eraksillan.name.mediagallery.local.model.LocalMediaTypeFilter
import eraksillan.name.mediagallery.paging.PagingListState
import eraksillan.name.mediagallery.paging.PagingMediaRepository
import eraksillan.name.mediagallery.paging.PagingViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

// https://medium.com/@cgaisl/how-to-pass-arguments-to-a-hiltviewmodel-from-compose-97c74a75f772
@HiltViewModel(assistedFactory = MediaListViewModel.ViewModelFactory::class)
class MediaListViewModel @AssistedInject constructor(
    @Assisted private val navController: NavController,
    @Assisted private val season: LocalMedia.Season,
    private val repository: PagingMediaRepository
) : PagingViewModel<LocalMedia>() {

    private val _state = MutableStateFlow(MediaListState.getDefault(season))
    val state = _state.asStateFlow()

    override var getPageCallback: (Int, Int) -> Flow<NetworkResult<List<LocalMedia>>> =
        { page: Int, pageSize: Int ->
            repository.getSeasonMedias(
                year = _state.value.year,
                season = _state.value.season,
                page = page,
                pageSize = pageSize,
                filter = _state.value.type,
                continuing = _state.value.type.continuing
            )
                .map {
                    when (it) {
                        is NetworkResult.Success -> NetworkResult.Success(it.data.mediaList)
                        is NetworkResult.Error -> NetworkResult.Error(it.code, it.message)
                        is NetworkResult.Exception -> NetworkResult.Exception(it.e)
                    }
                }
        }

    init {
        getPageData()
    }

    fun navigateToDetail() {
        navController.navigate(ComposeScreen.MEDIA_DETAIL.name)
    }

    fun onEvent(action: MediaListAction) {
        when (action) {
            is MediaListAction.GetNextPage -> {
                getPageData()
            }

            is MediaListAction.MediaTypeSelected -> {
                _state.update { it.copy(type = LocalMediaTypeFilter.entries[action.index]) }
                reset()
                getPageData()
            }

            is MediaListAction.SortTypeClicked -> {
                val listCopy = list.toList()
                reset()
                listState = PagingListState.PAGINATION_EXHAUST
                when (action.index) {
                    // Sort by members count
                    0 -> list.addAll(listCopy.sortedBy { it.members }.asReversed())
                    // Sort by start date
                    1 -> list.addAll(listCopy.sortedBy { it.aired.from?.epochSeconds })
                    // Sort by score
                    2 -> list.addAll(listCopy.sortedBy { it.score }.asReversed())
                }
            }

            is MediaListAction.NavigateToDetail -> {
                navigateToDetail()
            }
        }
    }

    @AssistedFactory
    interface ViewModelFactory {
        fun create(navController: NavController, season: LocalMedia.Season): MediaListViewModel
    }
}
