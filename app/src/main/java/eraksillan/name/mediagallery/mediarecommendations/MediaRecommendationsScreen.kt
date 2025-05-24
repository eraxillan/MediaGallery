package eraksillan.name.mediagallery.mediarecommendations

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import eraksillan.name.mediagallery.local.model.LocalMedia
import eraksillan.name.mediagallery.local.utils.NavigationHelpers
import eraksillan.name.mediagallery.navigation.Route
import kotlin.reflect.typeOf

fun NavGraphBuilder.mediaRecommendationsScreen(navController: NavController) {
    composable<Route.MediaRecommendations>(
        typeMap = mapOf(typeOf<List<LocalMedia.Recommendation>>() to NavigationHelpers.parcelableListType<LocalMedia.Recommendation>())
    ) { backStackEntry ->
        val route: Route.MediaRecommendations = backStackEntry.toRoute()

        val viewModel =
            hiltViewModel<MediaRecommendationsViewModel, MediaRecommendationsViewModel.ViewModelFactory> { factory ->
                factory.create(navController, route.data)
            }

        MediaRecommendationsCompose(data = route.data, viewModel = viewModel)
    }
}
