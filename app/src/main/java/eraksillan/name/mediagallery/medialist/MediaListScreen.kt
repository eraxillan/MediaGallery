package eraksillan.name.mediagallery.medialist

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import eraksillan.name.mediagallery.ComposeScreen

fun NavGraphBuilder.mediaListScreen(navController: NavController) {
    composable(ComposeScreen.MEDIA_LIST.name) {
        val viewModel =
            hiltViewModel<MediaListViewModel, MediaListViewModel.ViewModelFactory> { factory ->
                factory.create(navController)
            }

        MediaListCompose(viewModel = viewModel)
    }
}
