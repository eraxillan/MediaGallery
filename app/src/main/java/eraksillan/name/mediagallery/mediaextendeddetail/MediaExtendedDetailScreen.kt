package eraksillan.name.mediagallery.mediaextendeddetail

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import eraksillan.name.mediagallery.local.model.LocalMedia
import eraksillan.name.mediagallery.mediadetail.LocalMediaType
import eraksillan.name.mediagallery.navigation.Route
import kotlin.reflect.typeOf

fun NavGraphBuilder.mediaExtendedDetailScreen(navController: NavController) {
    composable<Route.MediaExtendedDetail>(
        typeMap = mapOf(typeOf<LocalMedia>() to LocalMediaType)
    ) { backStackEntry ->
        val route: Route.MediaExtendedDetail = backStackEntry.toRoute()

        val viewModel =
            hiltViewModel<MediaExtendedDetailViewModel, MediaExtendedDetailViewModel.ViewModelFactory> { factory ->
                factory.create(navController, route.data)
            }

        MediaExtendedDetailCompose(data = route.data, viewModel = viewModel)
    }
}
