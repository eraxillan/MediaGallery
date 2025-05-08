package eraksillan.name.mediagallery.mediastaff

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import eraksillan.name.mediagallery.local.model.LocalMedia
import eraksillan.name.mediagallery.local.utils.NavigationHelpers
import eraksillan.name.mediagallery.navigation.Route
import kotlin.reflect.typeOf

fun NavGraphBuilder.mediaStaffScreen(navController: NavController) {
    composable<Route.MediaStaff>(
        typeMap = mapOf(typeOf<List<LocalMedia.Person>>() to NavigationHelpers.parcelableListType<LocalMedia.Person>())
    ) { backStackEntry ->
        val route: Route.MediaStaff = backStackEntry.toRoute()

        val viewModel =
            hiltViewModel<MediaStaffViewModel, MediaStaffViewModel.ViewModelFactory> { factory ->
                factory.create(navController, route.data)
            }

        MediaStaffCompose(data = route.data, viewModel = viewModel)
    }
}
