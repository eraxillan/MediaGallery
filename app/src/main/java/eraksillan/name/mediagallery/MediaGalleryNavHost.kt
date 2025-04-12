package eraksillan.name.mediagallery

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import eraksillan.name.mediagallery.fullscreenpicture.fullScreenPictureScreen
import eraksillan.name.mediagallery.mediacast.mediaCastScreen
import eraksillan.name.mediagallery.mediadetail.mediaDetailScreen
import eraksillan.name.mediagallery.mediaextendeddetail.mediaExtendedDetailScreen
import eraksillan.name.mediagallery.medialist.mediaListScreen
import eraksillan.name.mediagallery.navigation.Route
import eraksillan.name.mediagallery.seasonlist.seasonScreen

@Composable
fun MediaGalleryNavHost(
    startDestination: Route,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination,
        popExitTransition = {
            scaleOut(
                targetScale = 0.9f,
                transformOrigin = TransformOrigin(pivotFractionX = 0.5f, pivotFractionY = 0.5f),
            )
        },
        popEnterTransition = {
            EnterTransition.None
        },
        modifier = modifier,
    ) {
        mediaListScreen(navController = navController)
        mediaDetailScreen(navController = navController)
        mediaExtendedDetailScreen(navController = navController)
        seasonScreen(navController = navController)
        fullScreenPictureScreen(navController = navController)
        mediaCastScreen(navController = navController)
    }
}
