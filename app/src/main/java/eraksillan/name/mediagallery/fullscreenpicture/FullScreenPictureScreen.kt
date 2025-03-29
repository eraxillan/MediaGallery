package eraksillan.name.mediagallery.fullscreenpicture

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import eraksillan.name.mediagallery.navigation.Route

fun NavGraphBuilder.fullScreenPictureScreen(navController: NavController) {
    composable<Route.FullScreenPicture> { backStackEntry ->
        val route: Route.FullScreenPicture = backStackEntry.toRoute()

        val viewModel =
            hiltViewModel<FullScreenPictureViewModel, FullScreenPictureViewModel.ViewModelFactory> { factory ->
                factory.create(navController)
            }

        FullScreenPictureCompose(route.url, viewModel)
    }
}
