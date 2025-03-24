package eraksillan.name.mediagallery.medialist

import androidx.navigation.NavController
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import eraksillan.name.mediagallery.ComposeScreen
import eraksillan.name.mediagallery.R
import eraksillan.name.mediagallery.architecture.NetworkResult
import eraksillan.name.mediagallery.local.model.LocalMedia
import eraksillan.name.mediagallery.local.model.LocalMediaFilter
import eraksillan.name.mediagallery.local.model.LocalMediaTypeFilter
import eraksillan.name.mediagallery.paging.PagingMediaRepository
import eraksillan.name.mediagallery.paging.PagingViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock.System
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

// https://medium.com/@cgaisl/how-to-pass-arguments-to-a-hiltviewmodel-from-compose-97c74a75f772
@HiltViewModel(assistedFactory = MediaListViewModel.ViewModelFactory::class)
class MediaListViewModel @AssistedInject constructor(
    @Assisted private val navController: NavController,
    private val repository: PagingMediaRepository
) : PagingViewModel<LocalMedia>() {

    val mediaFilter: LocalMediaFilter = getDefaultMediaFilter()

    override var callback: (Int, Int) -> Flow<NetworkResult<List<LocalMedia>>> =
        { page: Int, pageSize: Int ->
            repository.getSeasonMedias(
                year = mediaFilter.year,
                season = mediaFilter.season,
                page = page,
                pageSize = pageSize,
                filter = mediaFilter.type,
                continuing = mediaFilter.continuing
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

    fun getSeasonTriple(): SeasonInfoTriple? {
        val currentYear = System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .year
        val previousYear = currentYear - 1
        return when (getCurrentSeason()) {
            LocalMedia.Season.WINTER -> SeasonInfoTriple(
                data = listOf(
                    SeasonInfo(
                        value = LocalMedia.Season.FALL,
                        year = previousYear,
                        tabTitleResId = R.string.fall
                    ),
                    SeasonInfo(
                        value = LocalMedia.Season.WINTER,
                        year = currentYear,
                        tabTitleResId = R.string.winter
                    ),
                    SeasonInfo(
                        value = LocalMedia.Season.SPRING,
                        year = currentYear,
                        tabTitleResId = R.string.spring
                    )
                )
            )

            LocalMedia.Season.SPRING -> SeasonInfoTriple(
                data = listOf(
                    SeasonInfo(
                        value = LocalMedia.Season.WINTER,
                        year = currentYear,
                        tabTitleResId = R.string.winter
                    ),
                    SeasonInfo(
                        value = LocalMedia.Season.SPRING,
                        year = currentYear,
                        tabTitleResId = R.string.spring
                    ),
                    SeasonInfo(
                        value = LocalMedia.Season.SUMMER,
                        year = currentYear,
                        tabTitleResId = R.string.summer
                    )
                )
            )

            LocalMedia.Season.SUMMER -> SeasonInfoTriple(
                data = listOf(
                    SeasonInfo(
                        value = LocalMedia.Season.SPRING,
                        year = currentYear,
                        tabTitleResId = R.string.spring
                    ),
                    SeasonInfo(
                        value = LocalMedia.Season.SUMMER,
                        year = currentYear,
                        tabTitleResId = R.string.summer
                    ),
                    SeasonInfo(
                        value = LocalMedia.Season.FALL,
                        year = currentYear,
                        tabTitleResId = R.string.fall
                    )
                )
            )

            LocalMedia.Season.FALL -> SeasonInfoTriple(
                listOf(
                    SeasonInfo(
                        value = LocalMedia.Season.SUMMER,
                        year = previousYear,
                        tabTitleResId = R.string.summer
                    ),
                    SeasonInfo(
                        value = LocalMedia.Season.FALL,
                        year = currentYear,
                        tabTitleResId = R.string.fall
                    ),
                    SeasonInfo(
                        value = LocalMedia.Season.WINTER,
                        year = currentYear,
                        tabTitleResId = R.string.winter
                    )
                )
            )

            LocalMedia.Season.UNKNOWN -> null
        }
    }

    fun navigateToDetail() {
        navController.navigate(ComposeScreen.MEDIA_DETAIL.name)
    }

    private fun getCurrentSeason(): LocalMedia.Season {
        val currentMonthNo = System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .monthNumber
        return when (currentMonthNo) {
            12, 1, 2 -> LocalMedia.Season.WINTER
            3, 4, 5 -> LocalMedia.Season.SPRING
            6, 7, 8 -> LocalMedia.Season.SUMMER
            9, 10, 11 -> LocalMedia.Season.FALL
            else -> LocalMedia.Season.UNKNOWN
        }
    }

    private fun getDefaultMediaFilter(): LocalMediaFilter {
        return LocalMediaFilter(
            year = System.now().toLocalDateTime(TimeZone.currentSystemDefault()).year,
            season = getCurrentSeason(),
            type = LocalMediaTypeFilter.TV_NEW,
            continuing = false
        )
    }

    data class SeasonInfo(
        val value: LocalMedia.Season,
        val year: Int,
        val tabTitleResId: Int,
    )

    data class SeasonInfoTriple(
        val data: List<SeasonInfo>
    )

    @AssistedFactory
    interface ViewModelFactory {
        fun create(navController: NavController): MediaListViewModel
    }
}
