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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
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
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
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
import eraksillan.name.mediagallery.local.utils.mockCast
import eraksillan.name.mediagallery.local.utils.mockDiscussions
import eraksillan.name.mediagallery.local.utils.mockMedia
import eraksillan.name.mediagallery.local.utils.mockNews
import eraksillan.name.mediagallery.local.utils.mockRecommendations
import eraksillan.name.mediagallery.local.utils.mockRelations
import eraksillan.name.mediagallery.local.utils.mockReviews
import eraksillan.name.mediagallery.local.utils.mockStaff
import eraksillan.name.mediagallery.local.utils.mockStatistics
import eraksillan.name.mediagallery.local.utils.mockThemes
import eraksillan.name.mediagallery.local.utils.monthAndDayText
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
            casts = viewModel.castsPagingVM.list,
            staff = viewModel.staffPagingVM.list,
            themes = viewModel.themesPagingVM.list.firstOrNull(),
            reviews = viewModel.reviewsPagingVM.list,
            recommendations = viewModel.recommendationsPagingVM.list,
            news = viewModel.newsPagingVM.list,
            discussions = viewModel.discussionsPagingVM.list,
            statistics = viewModel.statisticsPagingVM.list.firstOrNull(),
            onEvent = { viewModel.onEvent(it) },
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding())
        )
    }
}

@Suppress("LongParameterList")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
private fun MediaDetailContentCompose(
    data: LocalMedia,
    pictures: List<LocalMedia.Images>,
    videos: List<LocalMediaVideos.Trailer>,
    relations: List<LocalMedia.Relation>,
    casts: List<LocalMedia.Cast>,
    staff: List<LocalMedia.Person>,
    themes: LocalMedia.Themes?,
    reviews: List<LocalMedia.Review>,
    recommendations: List<LocalMedia.Recommendation>,
    news: List<LocalMedia.NewsItem>,
    discussions: List<LocalMedia.Discussion>,
    statistics: LocalMedia.Statistics?,
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

        item {
            MediaRelationsCompose(relations, onEvent)
        }

        item {
            val casts = casts.sortedBy { it.favorites }.asReversed().takeWhile { it.favorites > CAST_FAVORITES_THRESHOLD }
            MediaCastCompose(casts, onEvent)
        }

        item {
            val casts = casts.sortedBy { it.favorites }.asReversed()
            Box(modifier = Modifier.fillMaxWidth()) {
                TextButton(
                    onClick = { onEvent(MediaDetailAction.MoreCastClicked(casts)) },
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    Text(text = stringResource(R.string.more_cast))
                }
            }
        }

        item {
            val staff = staff.sortedBy { it.person.malId }.asReversed().take(STAFF_MAX_COUNT)
            MediaStaffCompose(staff, onEvent)
        }

        item {
            val staff = staff.sortedBy { it.person.malId }.asReversed()
            Box(modifier = Modifier.fillMaxWidth()) {
                TextButton(
                    onClick = { onEvent(MediaDetailAction.MoreStaffClicked(staff)) },
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    Text(text = stringResource(R.string.more_staff))
                }
            }
        }

        item {
            if (themes != null) {
                MediaThemesCompose(themes)
            }
        }

        item {
            MediaReviewsCompose(reviews.take(REVIEW_MAX_COUNT), data.episodes, onEvent)
        }

        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                TextButton(
                    onClick = { onEvent(MediaDetailAction.MoreReviewsClicked(reviews)) },
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    Text(text = stringResource(R.string.more_reviews))
                }
            }
        }

        item {
            MediaRecommendationsCompose(recommendations, onEvent)
        }

        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                TextButton(
                    onClick = { onEvent(MediaDetailAction.MoreRecommendationsClicked(recommendations)) },
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    Text(text = stringResource(R.string.more_recommendations))
                }
            }
        }

        item {
            MediaNewsCompose(news, onEvent)
        }

        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                TextButton(
                    onClick = { onEvent(MediaDetailAction.MoreNewsClicked(news)) },
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    Text(text = stringResource(R.string.more_news))
                }
            }
        }

        item {
            MediaDiscussionsCompose(discussions, onEvent)
        }

        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                TextButton(
                    onClick = { onEvent(MediaDetailAction.MoreDiscussionsClicked(discussions)) },
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    Text(text = stringResource(R.string.more_discussions))
                }
            }
        }

        item {
            MediaWatchingStatisticsCompose(statistics)
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

@Composable
private fun MediaRelationsCompose(
    relations: List<LocalMedia.Relation>,
    onEvent: (MediaDetailAction) -> Unit
) {
    Column {
        relations.forEach { relation ->
            val relationName = relation.entry.firstOrNull()?.name
            val malId = relation.entry.firstOrNull()?.malId ?: -1
            if (relationName != null) {
                Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(relation.type.titleResId), modifier = Modifier
                            .weight(0.3f)
                            .padding(top = 14.dp)
                    )
                    TextButton(
                        onClick = { onEvent(MediaDetailAction.RelationClicked(malId)) },
                        modifier = Modifier.weight(0.7f)
                    ) {
                        Text(text = relationName)
                    }
                }
            }
        }
    }
}

@Composable
private fun MediaCastCompose(casts: List<LocalMedia.Cast>, onEvent: (MediaDetailAction) -> Unit) {
    val imageStub = painterResource(id = R.drawable.media_item_placeholder)
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(all = 16.dp),
        modifier = Modifier
            .fillMaxWidth()
            // FIXME: the crutch, we need to calculate height dynamically somehow
            .height(400.dp)
    ) {
        for (i in 0..casts.size - 1) {
            val cast = casts[i]
            item {
                Box(modifier = Modifier.fillMaxSize()) {
                    AsyncImage(
                        model = cast.character.images.jpeg?.base,
                        modifier = Modifier
                            .width(150.dp)
                            .height(250.dp)
                            .clickable { onEvent(MediaDetailAction.CharacterClicked(cast.character.malId)) },
                        placeholder = imageStub,
                        error = imageStub,
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        onError = { error ->
                            Log.e("MediaGallery", error.result.toString())
                        }
                    )

                    Box(modifier = Modifier.matchParentSize()) {
                        Box(
                            modifier = Modifier
                                .alpha(0.7f)
                                .background(Color.Black)
                                .align(Alignment.BottomStart)
                        ) {
                            Text(
                                text = cast.character.name,
                                style = MaterialTheme.typography.labelLarge,
                                color = Color.White,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 8.dp)
                            )
                        }
                    }
                }
            }
            item {
                Box(modifier = Modifier.fillMaxSize()) {
                    val imageUrl = cast.voiceActors
                        .firstOrNull { it.language == "Japanese" }?.person?.images?.jpeg?.base
                    val voiceActorName = cast.voiceActors
                        .firstOrNull { it.language == "Japanese" }?.person?.name ?: stringResource(R.string.unknown)

                    AsyncImage(
                        model = imageUrl,
                        modifier = Modifier
                            .width(150.dp)
                            .height(250.dp)
                            .clickable { onEvent(MediaDetailAction.CharacterClicked(cast.character.malId)) },
                        placeholder = imageStub,
                        error = imageStub,
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        onError = { error ->
                            Log.e("MediaGallery", error.result.toString())
                        }
                    )

                    Box(modifier = Modifier.matchParentSize()) {
                        Box(
                            modifier = Modifier
                                .alpha(0.7f)
                                .background(Color.Black)
                                .align(Alignment.BottomStart)
                        ) {
                            Text(
                                text = voiceActorName,
                                style = MaterialTheme.typography.labelLarge,
                                color = Color.White,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MediaStaffCompose(staff: List<LocalMedia.Person>, onEvent: (MediaDetailAction) -> Unit) {
    val imageStub = painterResource(id = R.drawable.media_item_placeholder)
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(all = 16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        items(staff) { person ->
            Box(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = person.person.images.jpeg?.base,
                    modifier = Modifier
                        .width(150.dp)
                        .height(250.dp)
                        .clickable { onEvent(MediaDetailAction.PersonClicked(person.person.malId)) },
                    placeholder = imageStub,
                    error = imageStub,
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    onError = { error ->
                        Log.e("MediaGallery", error.result.toString())
                    }
                )

                Box(modifier = Modifier.matchParentSize()) {
                    Box(
                        modifier = Modifier
                            .alpha(0.7f)
                            .background(Color.Black)
                            .align(Alignment.BottomStart)
                    ) {
                        Text(
                            text = person.person.name,
                            style = MaterialTheme.typography.labelLarge,
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MediaTheme(name: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.baseline_play_circle_outline_24),
            contentDescription = null,
            tint = Color.LightGray,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = name,
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .weight(1.0f)
        )
        Box(
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.surfaceVariant,
                    RoundedCornerShape(8.dp)
                )
        ) {
            Text(
                text = "MV",
                color = Color.White,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@Composable
private fun MediaThemesCompose(themes: LocalMedia.Themes) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = stringResource(R.string.opening_theme), color = MaterialTheme.colorScheme.outline)
        Column {
            themes.openings.forEach {
                MediaTheme(it)
            }
        }
        Spacer(Modifier.height(8.dp))
        Text(text = stringResource(R.string.ending_theme), color = MaterialTheme.colorScheme.outline)
        themes.endings.forEach {
            Row {
                MediaTheme(it)
            }
        }
    }
}

@Composable
private fun MediaReviewCompose(review: LocalMedia.Review, episodes: Int?, onEvent: (MediaDetailAction) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onEvent(MediaDetailAction.ReviewClicked(review)) })
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.outline_star_outline_24),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            // 9/10 by Marinate1016
            Text(
                text = stringResource(R.string.review_title, review.score, review.user.name),
                style = MaterialTheme.typography.titleMedium
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            // 13/13 ep, 241 helpful   Mar 29, 2025
            Text(
                text = stringResource(
                    R.string.review_subtitle,
                    (review.episodesWatched?.toString() ?: episodes?.toString()) ?: "N/A",
                    episodes?.toString() ?: "N/A",
                    review.reactions.overall
                ),
                modifier = Modifier.align(Alignment.TopStart)
            )
            Text(
                text = monthAndDayText(review.date),
                modifier = Modifier.align(Alignment.TopEnd)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        // Two lines of text with ellipsis
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = review.review, maxLines = 2, overflow = TextOverflow.Ellipsis)
    }
}

@Composable
private fun MediaReviewsCompose(reviews: List<LocalMedia.Review>, episodes: Int?, onEvent: (MediaDetailAction) -> Unit) {
    Spacer(modifier = Modifier.height(32.dp))
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        reviews.forEach {
            MediaReviewCompose(it, episodes, onEvent)
        }
    }
}

@Composable
private fun MediaRecommendationsCompose(recommendations: List<LocalMedia.Recommendation>, onEvent: (MediaDetailAction) -> Unit) {
    val imageStub = painterResource(id = R.drawable.media_item_placeholder)
    Spacer(modifier = Modifier.height(16.dp))
    LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        items(recommendations, { it.entry.malId }) { recommendation ->
            AsyncImage(
                model = recommendation.entry.images?.jpeg?.base,
                modifier = Modifier
                    .width(100.dp)
                    .height(150.dp)
                    .clickable { onEvent(MediaDetailAction.RecommendationClicked(recommendation)) },
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

@Composable
private fun MediaNewsCompose(news: List<LocalMedia.NewsItem>, onEvent: (MediaDetailAction) -> Unit) {
    val imageStub = painterResource(id = R.drawable.media_item_placeholder)
    Spacer(modifier = Modifier.height(16.dp))
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        news.take(2).forEach { newsItem ->
            Row {
                AsyncImage(
                    model = newsItem.images?.jpeg?.base,
                    modifier = Modifier
                        .width(100.dp)
                        .height(150.dp)
                        .clickable { onEvent(MediaDetailAction.NewsItemClicked(newsItem)) },
                    placeholder = imageStub,
                    error = imageStub,
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    onError = { error ->
                        Log.e("MediaGallery", error.result.toString())
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = newsItem.title ?: stringResource(R.string.not_applicable),
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = monthAndDayText(newsItem.date),
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = stringResource(R.string.comments_count, newsItem.comments ?: 0),
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
        }
    }
}

@Composable
private fun MediaDiscussionsCompose(discussions: List<LocalMedia.Discussion>, onEvent: (MediaDetailAction) -> Unit) {
    Spacer(modifier = Modifier.height(16.dp))
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        discussions.take(4).forEach { discussion ->
            Column {
                Text(text = discussion.title.orEmpty(), maxLines = 2, overflow = TextOverflow.Ellipsis)
                Text(
                    text = stringResource(
                        R.string.discussion_description,
                        discussion.comments ?: 0,
                        monthAndDayText(discussion.date), discussion.authorUserName.orEmpty()
                    )
                )
            }
        }
    }
}

@Composable
private fun MediaWatchingStatisticsBarCompose(watching: Int, part: Float) {
    val textMeasurer = rememberTextMeasurer()
    val textToDraw = String.format(Locale.US, "%,d", watching)
    val style = MaterialTheme.typography.labelLarge
    val textLayoutResult = remember(textToDraw, style) {
        textMeasurer.measure(textToDraw, style)
    }

    Box {
        Text(
            text = "",
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    val barWidth = part * size.width
                    drawRect(color = Color.Blue, size = Size(barWidth, size.height))

                    val textToDrawRightX = size.width - textLayoutResult.size.width - 8.dp.toPx()
                    val (textColor, textToDrawX) = if (textToDrawRightX >= barWidth) {
                        Color.Black to barWidth + 8.dp.toPx()
                    } else {
                        Color.White to barWidth - textLayoutResult.size.width - 8.dp.toPx()
                    }
                    val textToDrawY = (size.height - textLayoutResult.size.height) / 2f

                    drawText(
                        textMeasurer = textMeasurer,
                        text = textToDraw,
                        style = style.copy(color = textColor),
                        topLeft = Offset(
                            x = textToDrawX,
                            y = textToDrawY
                        )
                    )
                }
        )
    }
}

@Composable
private fun MediaWatchingStatisticsCompose(statistics: LocalMedia.Statistics?) {
    val statistics = statistics ?: return

    val watchingCount = listOf(statistics.watching, statistics.completed, statistics.onHold, statistics.dropped, statistics.planToWatch)
    val watchingCountWidthPart = watchingCount.map { 1f * it / watchingCount.max() }

    Spacer(modifier = Modifier.height(16.dp))

    Row {
        Column(horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(text = stringResource(R.string.watching))
            Text(text = stringResource(R.string.completed))
            Text(text = stringResource(R.string.on_hold))
            Text(text = stringResource(R.string.dropped))
            Text(text = stringResource(R.string.plan_to_watch))
        }

        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            for (index in 0..watchingCount.size - 1) {
                MediaWatchingStatisticsBarCompose(watchingCount[index], watchingCountWidthPart[index])
            }

            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(
                        R.string.all_members,
                        String.format(Locale.US, "%,d", statistics.total)
                    ),
                    modifier = Modifier.align(Alignment.TopEnd)
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Row {
        Column(horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.spacedBy(8.dp)) {
            for (index in 10 downTo 1) {
                Text(text = index.toString())
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            val maxVotes = statistics.scores.maxBy { it.votes }.votes
            for (item in statistics.scores.asReversed()) {
                MediaWatchingStatisticsBarCompose(item.votes, 1f * item.votes / maxVotes)
            }

            val voteSum = statistics.scores.sumOf { it.votes }
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(
                        R.string.scored_members,
                        String.format(Locale.US, "%,d", voteSum)
                    ),
                    modifier = Modifier.align(Alignment.TopEnd)
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(16.dp))
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
            relations = mockRelations,
            casts = mockCast,
            staff = mockStaff,
            themes = mockThemes,
            reviews = mockReviews,
            recommendations = mockRecommendations,
            news = mockNews,
            discussions = mockDiscussions,
            statistics = mockStatistics,
            { }
        )
    }
}

private const val CAST_FAVORITES_THRESHOLD = 150
private const val STAFF_MAX_COUNT = 10
private const val REVIEW_MAX_COUNT = 3
