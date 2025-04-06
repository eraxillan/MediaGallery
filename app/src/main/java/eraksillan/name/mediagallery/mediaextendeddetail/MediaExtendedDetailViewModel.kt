package eraksillan.name.mediagallery.mediaextendeddetail

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import eraksillan.name.mediagallery.architecture.NetworkResult
import eraksillan.name.mediagallery.local.model.LocalMedia
import eraksillan.name.mediagallery.paging.PagingMediaRepository
import eraksillan.name.mediagallery.paging.PagingViewModel
import kotlinx.coroutines.flow.map

@HiltViewModel(assistedFactory = MediaExtendedDetailViewModel.ViewModelFactory::class)
class MediaExtendedDetailViewModel @AssistedInject constructor(
    @Assisted private val navController: NavController,
    @Assisted private val data: LocalMedia,
    private val repository: PagingMediaRepository,
) : ViewModel() {

    val externalLinksPagingVM = PagingViewModel<LocalMedia.Link>(
        getPageCallback = { page: Int, pageSize: Int ->
            repository.getMediaExternalLinks(data.malId)
                .map {
                    when (it) {
                        is NetworkResult.Success -> NetworkResult.Success(it.data.data)
                        is NetworkResult.Error -> NetworkResult.Error(it.code, it.message)
                        is NetworkResult.Exception -> NetworkResult.Exception(it.e)
                    }
                }
        }
    )

    val moreInfoPagingVM = PagingViewModel<String>(
        getPageCallback = { page: Int, pageSize: Int ->
            repository.getMediaMoreInfo(data.malId)
                .map {
                    when (it) {
                        is NetworkResult.Success -> NetworkResult.Success(listOf(it.data.data))
                        is NetworkResult.Error -> NetworkResult.Error(it.code, it.message)
                        is NetworkResult.Exception -> NetworkResult.Exception(it.e)
                    }
                }
        }
    )

    init {
        externalLinksPagingVM.getPageData()
        moreInfoPagingVM.getPageData()
    }

    fun onEvent(action: MediaExtendedDetailAction) {
        when (action) {
            is MediaExtendedDetailAction.NavigateBackClicked -> navController.popBackStack()
            is MediaExtendedDetailAction.ProducerClicked -> action.uriHandler.openUri(action.url)
            is MediaExtendedDetailAction.ExternalLinkClicked -> action.uriHandler.openUri(action.url)
        }
    }

    @AssistedFactory
    interface ViewModelFactory {
        fun create(navController: NavController, data: LocalMedia): MediaExtendedDetailViewModel
    }
}
