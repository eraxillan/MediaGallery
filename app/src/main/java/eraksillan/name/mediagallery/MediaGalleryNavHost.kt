package eraksillan.name.mediagallery

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import eraksillan.name.mediagallery.mediadetail.mediaDetailScreen
import eraksillan.name.mediagallery.medialist.mediaListScreen

@Composable
fun MediaGalleryNavHost(
    startDestination: String,
    initNavController: (NavController) -> Unit,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    initNavController.invoke(navController)

    val systemUiController = rememberSystemUiController()

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        mediaListScreen(navController = navController)
        mediaDetailScreen(navController = navController)
    }
}

enum class ComposeScreen {
    MEDIA_LIST,
    MEDIA_DETAIL,
}
