package eraksillan.name.mediagallery.medianews

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import eraksillan.name.mediagallery.local.model.LocalMedia
import eraksillan.name.mediagallery.local.utils.NavigationHelpers
import eraksillan.name.mediagallery.navigation.Route
import kotlin.reflect.typeOf

fun NavGraphBuilder.mediaNewsScreen(navController: NavController) {
    composable<Route.MediaNews>(
        typeMap = mapOf(typeOf<List<LocalMedia.NewsItem>>() to NavigationHelpers.parcelableListType<LocalMedia.NewsItem>())
    ) { backStackEntry ->
        val route: Route.MediaNews = backStackEntry.toRoute()

        val viewModel =
            hiltViewModel<MediaNewsViewModel, MediaNewsViewModel.ViewModelFactory> { factory ->
                factory.create(navController, route.data)
            }

        MediaNewsCompose(data = route.data, viewModel = viewModel)
    }
}
