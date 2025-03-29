package eraksillan.name.mediagallery.mediadetail

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import eraksillan.name.mediagallery.local.model.LocalMedia
import eraksillan.name.mediagallery.navigation.Route
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf

fun NavGraphBuilder.mediaDetailScreen(navController: NavController) {
    composable<Route.MediaDetail>(
        typeMap = mapOf(typeOf<LocalMedia>() to LocalMediaType)
    ) { backStackEntry ->
        val route: Route.MediaDetail = backStackEntry.toRoute()

        val viewModel =
            hiltViewModel<MediaDetailViewModel, MediaDetailViewModel.ViewModelFactory> { factory ->
                factory.create(navController, route.data)
            }

        MediaDetailCompose(data = route.data, viewModel = viewModel)
    }
}

val LocalMediaType = object : NavType<LocalMedia>(
    isNullableAllowed = false
) {
    override fun get(bundle: Bundle, key: String): LocalMedia? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, LocalMedia::class.java)
        } else {
            @Suppress("DEPRECATION")
            bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): LocalMedia {
        return Json.decodeFromString<LocalMedia>(value)
    }

    override fun serializeAsValue(value: LocalMedia): String {
        return Uri.encode(Json.encodeToString(value))
    }

    override fun put(bundle: Bundle, key: String, value: LocalMedia) {
        bundle.putParcelable(key, value)
    }

    override val name: String
        get() = LocalMedia::class.java.name
}
