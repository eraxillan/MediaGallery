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

    override var callback: (Int, Int) -> Flow<NetworkResult<List<LocalMedia>>> =
        { page: Int, pageSize: Int ->
            repository.getWeeklySchedules(page, pageSize)
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

    data class SeasonInfo(
        val value: LocalMedia.Season,
        val year: Int,
        val tabTitleResId: Int,
    )

    // `this` is the reserved Kotlin keyword, so use `current`
    data class SeasonInfoTriple(
        val last: SeasonInfo,
        val current: SeasonInfo,
        val next: SeasonInfo,
    )

    fun getSeasonTriple(): SeasonInfoTriple? {
        val currentYear = System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .year
        val previousYear = currentYear - 1
        return when (getCurrentSeason()) {
            LocalMedia.Season.WINTER -> SeasonInfoTriple(
                last = SeasonInfo(
                    value = LocalMedia.Season.FALL,
                    year = previousYear,
                    tabTitleResId = R.string.fall
                ),
                current = SeasonInfo(
                    value = LocalMedia.Season.WINTER,
                    year = currentYear,
                    tabTitleResId = R.string.winter
                ),
                next = SeasonInfo(
                    value = LocalMedia.Season.SPRING,
                    year = currentYear,
                    tabTitleResId = R.string.spring
                )
            )

            LocalMedia.Season.SPRING -> SeasonInfoTriple(
                last = SeasonInfo(
                    value = LocalMedia.Season.WINTER,
                    year = currentYear,
                    tabTitleResId = R.string.winter
                ),
                current = SeasonInfo(
                    value = LocalMedia.Season.SPRING,
                    year = currentYear,
                    tabTitleResId = R.string.spring
                ),
                next = SeasonInfo(
                    value = LocalMedia.Season.SUMMER,
                    year = currentYear,
                    tabTitleResId = R.string.summer
                )
            )

            LocalMedia.Season.SUMMER -> SeasonInfoTriple(
                last = SeasonInfo(
                    value = LocalMedia.Season.SPRING,
                    year = currentYear,
                    tabTitleResId = R.string.spring
                ),
                current = SeasonInfo(
                    value = LocalMedia.Season.SUMMER,
                    year = currentYear,
                    tabTitleResId = R.string.summer
                ),
                next = SeasonInfo(
                    value = LocalMedia.Season.FALL,
                    year = currentYear,
                    tabTitleResId = R.string.fall
                )
            )

            LocalMedia.Season.FALL -> SeasonInfoTriple(
                last = SeasonInfo(
                    value = LocalMedia.Season.SUMMER,
                    year = previousYear,
                    tabTitleResId = R.string.summer
                ),
                current = SeasonInfo(
                    value = LocalMedia.Season.FALL,
                    year = currentYear,
                    tabTitleResId = R.string.fall
                ),
                next = SeasonInfo(
                    value = LocalMedia.Season.WINTER,
                    year = currentYear,
                    tabTitleResId = R.string.winter
                )
            )

            LocalMedia.Season.UNKNOWN -> null
        }
    }

    @AssistedFactory
    interface ViewModelFactory {
        fun create(navController: NavController): MediaListViewModel
    }
}
