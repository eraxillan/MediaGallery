package eraksillan.name.mediagallery.mediacast

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import eraksillan.name.mediagallery.local.model.LocalMedia
import eraksillan.name.mediagallery.local.utils.NavigationHelpers
import eraksillan.name.mediagallery.navigation.Route
import kotlin.reflect.typeOf

fun NavGraphBuilder.mediaCastScreen(navController: NavController) {
    composable<Route.MediaCast>(
        typeMap = mapOf(typeOf<List<LocalMedia.Cast>>() to NavigationHelpers.parcelableListType<LocalMedia.Cast>())
    ) { backStackEntry ->
        val route: Route.MediaCast = backStackEntry.toRoute()

        val viewModel =
            hiltViewModel<MediaCastViewModel, MediaCastViewModel.ViewModelFactory> { factory ->
                factory.create(navController, route.data)
            }

        MediaCastCompose(data = route.data, viewModel = viewModel)
    }
}
