package eraksillan.name.mediagallery.mediadetail

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import eraksillan.name.mediagallery.architecture.NetworkResult
import eraksillan.name.mediagallery.local.model.LocalMedia
import eraksillan.name.mediagallery.local.model.LocalMediaVideos
import eraksillan.name.mediagallery.local.utils.createShareIntent
import eraksillan.name.mediagallery.local.utils.watchYoutube
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

    val videosPagingVM = PagingViewModel<LocalMediaVideos.Trailer>(
        getPageCallback = { page: Int, pageSize: Int ->
            repository.getMediaVideos(data.malId)
                .map {
                    when (it) {
                        is NetworkResult.Success -> NetworkResult.Success(it.data.data.promos)
                        is NetworkResult.Error -> NetworkResult.Error(it.code, it.message)
                        is NetworkResult.Exception -> NetworkResult.Exception(it.e)
                    }
                }
        }
    )

    val relationsPagingVM = PagingViewModel<LocalMedia.Relation>(
        getPageCallback = { page: Int, pageSize: Int ->
            repository.getMediaRelations(data.malId)
                .map {
                    when (it) {
                        is NetworkResult.Success -> NetworkResult.Success(it.data.data)
                        is NetworkResult.Error -> NetworkResult.Error(it.code, it.message)
                        is NetworkResult.Exception -> NetworkResult.Exception(it.e)
                    }
                }
        }
    )

    val castsPagingVM = PagingViewModel<LocalMedia.Cast>(
        getPageCallback = { page: Int, pageSize: Int ->
            repository.getMediaCast(data.malId)
                .map {
                    when (it) {
                        is NetworkResult.Success -> NetworkResult.Success(it.data.data)
                        is NetworkResult.Error -> NetworkResult.Error(it.code, it.message)
                        is NetworkResult.Exception -> NetworkResult.Exception(it.e)
                    }
                }
        }
    )

    val staffPagingVM = PagingViewModel<LocalMedia.Person>(
        getPageCallback = { page: Int, pageSize: Int ->
            repository.getMediaStaff(data.malId)
                .map {
                    when (it) {
                        is NetworkResult.Success -> NetworkResult.Success(it.data.data)
                        is NetworkResult.Error -> NetworkResult.Error(it.code, it.message)
                        is NetworkResult.Exception -> NetworkResult.Exception(it.e)
                    }
                }
        }
    )

    val themesPagingVM = PagingViewModel<LocalMedia.Themes>(
        getPageCallback = { page: Int, pageSize: Int ->
            repository.getMediaThemes(data.malId)
                .map {
                    when (it) {
                        is NetworkResult.Success -> NetworkResult.Success(listOf(it.data.data))
                        is NetworkResult.Error -> NetworkResult.Error(it.code, it.message)
                        is NetworkResult.Exception -> NetworkResult.Exception(it.e)
                    }
                }
        }
    )

    val reviewsPagingVM = PagingViewModel<LocalMedia.Review>(
        getPageCallback = { page: Int, pageSize: Int ->
            repository.getMediaReviews(data.malId)
                .map {
                    when (it) {
                        is NetworkResult.Success -> NetworkResult.Success(it.data.data)
                        is NetworkResult.Error -> NetworkResult.Error(it.code, it.message)
                        is NetworkResult.Exception -> NetworkResult.Exception(it.e)
                    }
                }
        }
    )

    val recommendationsPagingVM = PagingViewModel<LocalMedia.Recommendation>(
        getPageCallback = { page: Int, pageSize: Int ->
            repository.getMediaRecommendations(data.malId)
                .map {
                    when (it) {
                        is NetworkResult.Success -> NetworkResult.Success(it.data.data)
                        is NetworkResult.Error -> NetworkResult.Error(it.code, it.message)
                        is NetworkResult.Exception -> NetworkResult.Exception(it.e)
                    }
                }
        }
    )

    val newsPagingVM = PagingViewModel<LocalMedia.NewsItem>(
        getPageCallback = { page: Int, pageSize: Int ->
            repository.getMediaNews(data.malId)
                .map {
                    when (it) {
                        is NetworkResult.Success -> NetworkResult.Success(it.data.data)
                        is NetworkResult.Error -> NetworkResult.Error(it.code, it.message)
                        is NetworkResult.Exception -> NetworkResult.Exception(it.e)
                    }
                }
        }
    )

    val discussionsPagingVM = PagingViewModel<LocalMedia.Discussion>(
        getPageCallback = { page: Int, pageSize: Int ->
            repository.getMediaDiscussions(data.malId)
                .map {
                    when (it) {
                        is NetworkResult.Success -> NetworkResult.Success(it.data.data)
                        is NetworkResult.Error -> NetworkResult.Error(it.code, it.message)
                        is NetworkResult.Exception -> NetworkResult.Exception(it.e)
                    }
                }
        }
    )

    val statisticsPagingVM = PagingViewModel<LocalMedia.Statistics>(
        getPageCallback = { page: Int, pageSize: Int ->
            repository.getMediaStatistics(data.malId)
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
        imagesPagingVM.getPageData()
        videosPagingVM.getPageData()
        relationsPagingVM.getPageData()
        castsPagingVM.getPageData()
        staffPagingVM.getPageData()
        themesPagingVM.getPageData()
        reviewsPagingVM.getPageData()
        recommendationsPagingVM.getPageData()
        newsPagingVM.getPageData()
        discussionsPagingVM.getPageData()
        statisticsPagingVM.getPageData()
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

            is MediaDetailAction.GenreClicked -> {
                TODO()
            }

            is MediaDetailAction.VideoPreviewClicked -> {
                action.context.watchYoutube(action.id)
            }

            is MediaDetailAction.MoreInformationClicked -> {
                navController.navigate(Route.MediaExtendedDetail(data))
            }

            is MediaDetailAction.ProducerClicked -> {
                TODO()
            }

            is MediaDetailAction.ExternalLinkClicked -> {
                TODO()
            }

            is MediaDetailAction.RelationClicked -> {
                TODO()
            }

            is MediaDetailAction.CharacterClicked -> {
                TODO()
            }

            is MediaDetailAction.VoiceActorClicked -> {
                TODO()
            }

            is MediaDetailAction.MoreCastClicked -> {
                navController.navigate(Route.MediaCast(action.data))
            }

            is MediaDetailAction.PersonClicked -> {
                TODO()
            }

            is MediaDetailAction.MoreStaffClicked -> {
                navController.navigate(Route.MediaStaff(action.data))
            }

            is MediaDetailAction.ReviewClicked -> {
                TODO()
            }

            is MediaDetailAction.MoreReviewsClicked -> {
                navController.navigate(Route.MediaReviews(action.data))
            }

            is MediaDetailAction.RecommendationClicked -> {
                TODO()
            }

            is MediaDetailAction.MoreRecommendationsClicked -> {
                navController.navigate(Route.MediaRecommendations(action.data))
            }

            is MediaDetailAction.NewsItemClicked -> {
                TODO()
            }

            is MediaDetailAction.MoreNewsClicked -> {
                navController.navigate(Route.MediaNews(action.data))
            }

            is MediaDetailAction.DiscussionClicked -> {
                TODO()
            }

            is MediaDetailAction.MoreDiscussionsClicked -> {
                navController.navigate(Route.MediaDiscussions(action.data))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        imagesPagingVM.reset()
        videosPagingVM.reset()
        relationsPagingVM.reset()
        castsPagingVM.reset()
        staffPagingVM.reset()
        recommendationsPagingVM.reset()
        newsPagingVM.reset()
        discussionsPagingVM.reset()
        statisticsPagingVM.reset()
    }

    @AssistedFactory
    interface ViewModelFactory {
        fun create(navController: NavController, data: LocalMedia): MediaDetailViewModel
    }
}
