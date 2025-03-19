package eraksillan.name.mediagallery

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import eraksillan.name.mediagallery.ui.theme.MediaGalleryTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // https://dev.to/elozino/getting-started-with-splash-screen-in-jetpack-compose-144l
        val splashscreen = installSplashScreen()
        var keepSplashScreen = true
        splashscreen.setKeepOnScreenCondition {
            // TODO: !viewModel.isReady
            keepSplashScreen
        }
        lifecycleScope.launch {
            delay(2_000)
            keepSplashScreen = false
        }

        enableEdgeToEdge()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isNavigationBarContrastEnforced = false
        }

        setContent {
            MediaGalleryTheme {
                MediaGalleryNavHost(startDestination = ComposeScreen.MEDIA_LIST.name, {})
            }
        }
    }
}
