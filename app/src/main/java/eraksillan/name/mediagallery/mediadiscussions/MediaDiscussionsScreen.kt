package eraksillan.name.mediagallery.mediadiscussions

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import eraksillan.name.mediagallery.local.model.LocalMedia
import eraksillan.name.mediagallery.local.utils.NavigationHelpers
import eraksillan.name.mediagallery.navigation.Route
import kotlin.reflect.typeOf

fun NavGraphBuilder.mediaDiscussionsScreen(navController: NavController) {
    composable<Route.MediaDiscussions>(
        typeMap = mapOf(typeOf<List<LocalMedia.Discussion>>() to NavigationHelpers.parcelableListType<LocalMedia.Discussion>())
    ) { backStackEntry ->
        val route: Route.MediaDiscussions = backStackEntry.toRoute()

        val viewModel =
            hiltViewModel<MediaDiscussionsViewModel, MediaDiscussionsViewModel.ViewModelFactory> { factory ->
                factory.create(navController, route.data)
            }

        MediaDiscussionsCompose(data = route.data, viewModel = viewModel)
    }
}
