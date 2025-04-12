package eraksillan.name.mediagallery.mediadetail

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import eraksillan.name.mediagallery.local.model.LocalMedia
import eraksillan.name.mediagallery.local.utils.LocalMediaNavType
import eraksillan.name.mediagallery.navigation.Route
import kotlin.reflect.typeOf

fun NavGraphBuilder.mediaDetailScreen(navController: NavController) {
    composable<Route.MediaDetail>(
        typeMap = mapOf(typeOf<LocalMedia>() to LocalMediaNavType)
    ) { backStackEntry ->
        val route: Route.MediaDetail = backStackEntry.toRoute()

        val viewModel =
            hiltViewModel<MediaDetailViewModel, MediaDetailViewModel.ViewModelFactory> { factory ->
                factory.create(navController, route.data)
            }

        MediaDetailCompose(data = route.data, viewModel = viewModel)
    }
}
