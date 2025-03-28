package eraksillan.name.mediagallery.medialist

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import eraksillan.name.mediagallery.local.utils.getCurrentSeason
import eraksillan.name.mediagallery.local.utils.getNextSeason
import eraksillan.name.mediagallery.local.utils.getPreviousSeason
import eraksillan.name.mediagallery.navigation.Route
import eraksillan.name.mediagallery.seasonlist.SeasonListViewModel

fun NavGraphBuilder.mediaListScreen(navController: NavController) {
    composable<Route.MediaList> { backStackEntry ->
        val lastSeasonViewModel =
            hiltViewModel<MediaListViewModel, MediaListViewModel.ViewModelFactory>(
                key = "${getPreviousSeason()}",
                creationCallback = { it.create(navController, getPreviousSeason()) }
            )
        val currentSeasonViewModel =
            hiltViewModel<MediaListViewModel, MediaListViewModel.ViewModelFactory>(
                key = "${getCurrentSeason()}",
                creationCallback = { it.create(navController, getCurrentSeason()) }
            )
        val nextSeasonViewModel =
            hiltViewModel<MediaListViewModel, MediaListViewModel.ViewModelFactory>(
                key = "${getNextSeason()}",
                creationCallback = { it.create(navController, getNextSeason()) }
            )
        val archiveSeasonViewModel =
            hiltViewModel<SeasonListViewModel, SeasonListViewModel.ViewModelFactory>(
                creationCallback = { it.create(navController) }
            )

        MediaListCompose(listOf(lastSeasonViewModel, currentSeasonViewModel, nextSeasonViewModel), archiveSeasonViewModel)
    }
}
