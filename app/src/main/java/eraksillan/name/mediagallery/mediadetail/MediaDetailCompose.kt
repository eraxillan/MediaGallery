package eraksillan.name.mediagallery.mediadetail

import android.util.Log
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import eraksillan.name.mediagallery.R
import eraksillan.name.mediagallery.designsystem.ChipsFlowRow
import eraksillan.name.mediagallery.designsystem.ExpandableText
import eraksillan.name.mediagallery.local.model.LocalMedia
import eraksillan.name.mediagallery.local.model.LocalMediaVideos
import eraksillan.name.mediagallery.local.utils.displayDefaultTitle
import eraksillan.name.mediagallery.local.utils.englishTitle
import eraksillan.name.mediagallery.local.utils.mockMedia
import eraksillan.name.mediagallery.ui.theme.MediaGalleryTheme
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaDetailCompose(data: LocalMedia, viewModel: MediaDetailViewModel) {
    val state by viewModel.state.collectAsState()
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
                    Row {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                        )
                        Text(
                            text = stringResource(R.string.score, data.score?.toString() ?: stringResource(R.string.not_applicable)),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
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
            data = data,
            pictures = viewModel.imagesPagingVM.list,
            videos = viewModel.videosPagingVM.list,
            relations = viewModel.relationsPagingVM.list,
            onEvent = { viewModel.onEvent(it) },
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding())
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
private fun MediaDetailContentCompose(
    data: LocalMedia,
    pictures: List<LocalMedia.Images>,
    videos: List<LocalMediaVideos.Trailer>,
    relations: List<LocalMedia.Relation>,
    onEvent: (MediaDetailAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val imageStub = painterResource(id = R.drawable.media_item_placeholder)
    LazyColumn(modifier = modifier.padding(16.dp)) {
        item {
            MediaPicturesCompose(
                pictures = pictures,
                imageStub = imageStub,
                onEvent = onEvent
            )
        }

        item {
            MediaDefaultTitleCompose(data.displayDefaultTitle())
        }

        item {
            MediaStatisticsCompose(
                members = data.members,
                favorites = data.favorites,
                rank = data.rank,
                popularity = data.popularity
            )
        }

        item {
            MediaStatusCompose(
                type = data.type,
                status = data.status,
                year = data.year,
                episodes = data.episodes,
                duration = data.duration
            )
        }

        item {
            MediaGenresCompose(
                genres = data.genres,
                themes = data.themes,
                onEvent = onEvent
            )
        }

        item {
            MediaDescriptionCompose(data.synopsis)
        }

        item {
            MediaVideosCompose(imageStub, videos, onEvent)
        }

        item {
            MediaBasicInfoCompose(
                englishTitle = data.englishTitle(),
                season = data.season,
                year = data.year,
                source = data.source,
                aired = data.aired,
                rating = data.rating,
                studios = data.studios,
                licensors = data.licensors
            )
        }

        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                TextButton(
                    onClick = { onEvent(MediaDetailAction.MoreInformationClicked) },
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    Text(text = stringResource(R.string.more_information))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MediaPicturesCompose(
    pictures: List<LocalMedia.Images>,
    imageStub: Painter,
    onEvent: (MediaDetailAction) -> Unit
) {
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

@Composable
private fun MediaDefaultTitleCompose(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
    )
}

@Composable
private fun MediaStatisticsCompose(
    members: Int?,
    favorites: Int?,
    rank: Int?,
    popularity: Int?
) {
    val notApplicable = stringResource(R.string.not_applicable)
    val rank = rank?.toString() ?: notApplicable
    val popularity = popularity?.toString() ?: notApplicable
    val members = if (members != null) String.format(Locale.US, "%,d", members) else "N/A"
    val favorites = if (favorites != null) String.format(Locale.US, "%,d", favorites) else "N/A"

    Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth()) {
        Column(horizontalAlignment = Alignment.Start, modifier = Modifier.weight(0.5f)) {
            Text(text = stringResource(R.string.rank), color = MaterialTheme.colorScheme.outline)
            Text(text = rank)

            Text(
                text = stringResource(R.string.members),
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = members)
        }
        Column(horizontalAlignment = Alignment.Start, modifier = Modifier.weight(0.5f)) {
            Text(text = stringResource(R.string.popularity), color = MaterialTheme.colorScheme.outline)
            Text(text = popularity)

            Text(
                text = stringResource(R.string.favorites),
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = favorites, modifier = Modifier)
        }
    }
}

@Composable
private fun MediaStatusCompose(
    type: LocalMedia.Type?,
    status: LocalMedia.Status?,
    year: Int?,
    episodes: Int?,
    duration: String?
) {
    val typeAndYear = stringResource(type?.titleResId ?: R.string.not_applicable) + ", " + year
    val statusString = stringResource(status?.titleResId ?: R.string.not_applicable)
    val statusText = if (status == LocalMedia.Status.CURRENTLY_AIRING) {
        buildAnnotatedString {
            withStyle(SpanStyle(Color.Green)) { append(statusString) }
        }
    } else {
        buildAnnotatedString { append(statusString) }
    }
    var episodes = episodes?.toString()
    // Example: // "13 ep, 23 min"
    if (episodes != null) {
        episodes += (" ep, " + duration.toString().dropLast(7))
    } else {
        episodes = stringResource(R.string.not_applicable)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp)
            .background(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Spacer(modifier = Modifier.width(32.dp))
        Text(text = typeAndYear)
        Text(text = statusText)
        Text(text = episodes)
        Spacer(modifier = Modifier.width(32.dp))
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MediaGenresCompose(
    genres: List<LocalMedia.Entity>,
    themes: List<LocalMedia.Entity>,
    onEvent: (MediaDetailAction) -> Unit
) {
    val genres = (genres + themes).map { it.name }.sorted()
    ChipsFlowRow(genres, onClick = { index -> onEvent(MediaDetailAction.GenreClicked(index)) })
}

@Composable
private fun MediaDescriptionCompose(text: String?) {
    ExpandableText(
        text = text ?: stringResource(R.string.not_applicable),
        fontSize = MaterialTheme.typography.bodySmall.fontSize
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MediaVideosCompose(
    imageStub: Painter,
    videos: List<LocalMediaVideos.Trailer>,
    onEvent: (MediaDetailAction) -> Unit
) {
    HorizontalMultiBrowseCarousel(
        state = rememberCarouselState { videos.size },
        modifier = Modifier
            .padding(top = 16.dp, bottom = 16.dp)
            .fillMaxWidth()
            .height(250.dp),
        preferredItemWidth = 186.dp,
        itemSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) { index ->
        val context = LocalActivity.current!!
        val url = videos[index].trailer.images?.medium.toString()
        Box {
            // Media video preview image
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

            // Media video title
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .background(Color.Black)
            ) {
                Text(
                    text = videos[index].title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp)
                )
            }

            // Media video play button
            IconButton(
                onClick = {
                    onEvent(
                        MediaDetailAction.VideoPreviewClicked(
                            context,
                            videos[index].trailer.youtubeId.orEmpty()
                        )
                    )
                },
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(64.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.baseline_play_circle_outline_24),
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier.size(64.dp)
                )
            }
        }
    }
}

@Composable
private fun MediaBasicInfoCompose(
    englishTitle: String?,
    season: LocalMedia.Season?,
    year: Int?,
    source: String?,
    aired: LocalMedia.Aired,
    rating: LocalMedia.Rating?,
    studios: List<LocalMedia.Entity>,
    licensors: List<LocalMedia.Entity>
) {
    val unknownString = stringResource(R.string.unknown)
    val englishTitle = englishTitle ?: unknownString
    val source = source ?: stringResource(R.string.unknown)
    val season = if (season?.titleResId != null) {
        stringResource(season.titleResId) + " " + year
    } else {
        unknownString
    }
    val studio = if (studios.isNotEmpty()) {
        studios.joinToString(",") { it.name }
    } else {
        unknownString
    }
    val rating = if (rating != null) {
        stringResource(rating.titleResId)
    } else {
        unknownString
    }
    val licensor = if (licensors.isNotEmpty()) {
        licensors.joinToString(",") { it.name }
    } else {
        unknownString
    }

    Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth()) {
        Column(horizontalAlignment = Alignment.Start) {
            Text(text = stringResource(R.string.english), color = MaterialTheme.colorScheme.outline)
            Text(text = englishTitle)
        }
    }

    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Column(horizontalAlignment = Alignment.Start, modifier = Modifier.weight(0.5f)) {
            Text(text = stringResource(R.string.source), color = MaterialTheme.colorScheme.outline)
            Text(text = source)

            Text(
                text = stringResource(R.string.studio),
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = studio)

            Text(
                text = stringResource(R.string.rating),
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = rating)
        }
        Column(horizontalAlignment = Alignment.Start, modifier = Modifier.weight(0.5f)) {
            Text(text = stringResource(R.string.season), color = MaterialTheme.colorScheme.outline)
            Text(text = season)

            Text(
                text = stringResource(R.string.aired),
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = aired.displayString, modifier = Modifier)

            Text(
                text = stringResource(R.string.licensor),
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = licensor, modifier = Modifier)
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
            videos = emptyList(),
            relations = listOf(),
            { }
        )
    }
}
