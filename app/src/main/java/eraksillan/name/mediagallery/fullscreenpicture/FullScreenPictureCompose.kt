package eraksillan.name.mediagallery.fullscreenpicture

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import coil3.compose.AsyncImage
import eraksillan.name.mediagallery.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullScreenPictureCompose(url: String, viewModel: FullScreenPictureViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = stringResource(R.string.media_detail),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { viewModel.onEvent(FullScreenPictureAction.NavigateBackClicked) }) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
            )
        },
        modifier = Modifier
            .navigationBarsPadding()
            .imePadding(),
    ) { paddingValues ->
        FullScreenPictureContentCompose(url, Modifier.padding(top = paddingValues.calculateTopPadding()))
    }
}

@Composable
private fun FullScreenPictureContentCompose(url: String, modifier: Modifier = Modifier) {
    val imageStub = painterResource(id = R.drawable.media_item_placeholder)
    AsyncImage(
        model = url,
        modifier = modifier.fillMaxSize(),
        placeholder = imageStub,
        error = imageStub,
        contentScale = ContentScale.Crop,
        contentDescription = null,
        onError = { error ->
            Log.e("MediaGallery", error.result.toString())
        }
    )
}
