package eraksillan.name.mediagallery.mediadetail

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import eraksillan.name.mediagallery.ComposeScreen

fun NavGraphBuilder.mediaDetailScreen(navController: NavController) {
    composable(ComposeScreen.MEDIA_DETAIL.name) {
        val viewModel =
            hiltViewModel<MediaDetailViewModel, MediaDetailViewModel.ViewModelFactory> { factory ->
                factory.create(navController)
            }

        MediaDetailCompose(viewModel = viewModel)
    }
}
