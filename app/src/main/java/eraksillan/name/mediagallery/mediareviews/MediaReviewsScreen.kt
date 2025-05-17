package eraksillan.name.mediagallery.mediareviews

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import eraksillan.name.mediagallery.local.model.LocalMedia
import eraksillan.name.mediagallery.local.utils.NavigationHelpers
import eraksillan.name.mediagallery.navigation.Route
import kotlin.reflect.typeOf

fun NavGraphBuilder.mediaReviewsScreen(navController: NavController) {
    composable<Route.MediaReviews>(
        typeMap = mapOf(typeOf<List<LocalMedia.Review>>() to NavigationHelpers.parcelableListType<LocalMedia.Review>())
    ) { backStackEntry ->
        val route: Route.MediaReviews = backStackEntry.toRoute()

        val viewModel =
            hiltViewModel<MediaReviewsViewModel, MediaReviewsViewModel.ViewModelFactory> { factory ->
                factory.create(navController, route.data)
            }

        MediaReviewsCompose(data = route.data, viewModel = viewModel)
    }
}
