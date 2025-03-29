package eraksillan.name.mediagallery.mediadetail

import android.util.Log
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import eraksillan.name.mediagallery.R
import eraksillan.name.mediagallery.local.model.LocalMedia
import eraksillan.name.mediagallery.local.utils.mockMedia
import eraksillan.name.mediagallery.ui.theme.MediaGalleryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaDetailCompose(data: LocalMedia, viewModel: MediaDetailViewModel) {
    // TODO: implement
    val isFavorite = false

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
                    IconButton(onClick = { viewModel.onEvent(MediaDetailAction.NavigateBackClicked) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            if (isFavorite) {
                                viewModel.onEvent(MediaDetailAction.RemoveFromFavoritesClicked)
                            } else {
                                viewModel.onEvent(MediaDetailAction.AddToFavoritesClicked)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    val shareContentDescription = stringResource(R.string.menu_item_share_media)
                    val activity = LocalActivity.current ?: return@CenterAlignedTopAppBar
                    IconButton(
                        onClick = { viewModel.onEvent(MediaDetailAction.ShareMediaClicked(activity)) },
                        // Semantics in parent due to https://issuetracker.google.com/184825850
                        modifier = Modifier.semantics { contentDescription = shareContentDescription }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        },
        modifier = Modifier
            .navigationBarsPadding()
            .imePadding(),
    ) { paddingValues ->
        MediaDetailContentCompose(
            data,
            viewModel.imagesPagingVM.list,
            { viewModel.onEvent(it) },
            Modifier.padding(top = paddingValues.calculateTopPadding())
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MediaDetailContentCompose(
    data: LocalMedia,
    pictures: List<LocalMedia.Images>,
    onEvent: (MediaDetailAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        val imageStub = painterResource(id = R.drawable.media_item_placeholder)

        HorizontalMultiBrowseCarousel(
            state = rememberCarouselState { pictures.size },
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            preferredItemWidth = 186.dp,
            itemSpacing = 8.dp,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) { index ->
            val url = pictures[index].jpeg?.base.toString()
            AsyncImage(
                model = url,
                modifier = Modifier
                    .height(250.dp)
                    .clickable { onEvent(MediaDetailAction.FullScreenPictureClicked(url)) },
                placeholder = imageStub,
                error = imageStub,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                onError = { error ->
                    Log.e("MediaGallery", error.result.toString())
                }
            )
        }
    }
}

@Suppress("unused")
@Composable
@Preview(showBackground = true)
private fun MediaDetailComposePreview() {
    MediaGalleryTheme {
        MediaDetailContentCompose(
            data = mockMedia,
            pictures = emptyList<LocalMedia.Images>(),
            { }
        )
    }
}
