package eraksillan.name.mediagallery.medialist

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import eraksillan.name.mediagallery.ComposeScreen
import eraksillan.name.mediagallery.local.utils.getCurrentSeason
import eraksillan.name.mediagallery.local.utils.getNextSeason
import eraksillan.name.mediagallery.local.utils.getPreviousSeason

fun NavGraphBuilder.mediaListScreen(navController: NavController) {
    composable(ComposeScreen.MEDIA_LIST.name) {
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

        MediaListCompose(listOf(lastSeasonViewModel, currentSeasonViewModel, nextSeasonViewModel))
    }
}
