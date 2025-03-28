package eraksillan.name.mediagallery.medialist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eraksillan.name.mediagallery.R
import eraksillan.name.mediagallery.designsystem.ComboBox
import eraksillan.name.mediagallery.designsystem.ContextMenuCompose
import eraksillan.name.mediagallery.designsystem.SwipeableTabRow
import eraksillan.name.mediagallery.designsystem.TinyOutlinedReadOnlyTextField
import eraksillan.name.mediagallery.designsystem.fullWidthItem
import eraksillan.name.mediagallery.local.model.LocalMedia
import eraksillan.name.mediagallery.local.model.LocalMediaSortType
import eraksillan.name.mediagallery.local.model.LocalMediaTypeFilter
import eraksillan.name.mediagallery.local.utils.MediaSeasonInfo
import eraksillan.name.mediagallery.local.utils.getSeasonTriple
import eraksillan.name.mediagallery.paging.PagingListState
import eraksillan.name.mediagallery.seasonlist.SeasonListCompose
import eraksillan.name.mediagallery.seasonlist.SeasonListViewModel
import eraksillan.name.mediagallery.ui.theme.MediaGalleryTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MediaListCompose(viewModels: List<MediaListViewModel>, archiveSeasonViewModel: SeasonListViewModel) {
    val seasonTriple = getSeasonTriple() ?: return

    val tabTitles = listOf(
        R.string.last_tab,
        R.string.this_season,
        R.string.next_season,
        R.string.archive_seasons
    )

    Scaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .imePadding(),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                )
        ) {
            SwipeableTabRow(
                tabTitles = tabTitles.map { stringResource(it) },
                initialPageIndex = INITIAL_TAB_INDEX,
                pageContent = { index ->
                    when (index) {
                        LAST_TAB_INDEX, CURRENT_TAB_INDEX, NEXT_TAB_INDEX -> {
                            ActualSeasonCompose(viewModels[index], seasonTriple.data[index])
                        }

                        ARCHIVE_TAB_INDEX -> {
                            SeasonListCompose(archiveSeasonViewModel)
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun ActualSeasonCompose(
    viewModel: MediaListViewModel,
    seasonInfo: MediaSeasonInfo
) {
    // val state by viewModel.state.collectAsState()

    val listState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()

    val shouldStartPaginate by remember {
        derivedStateOf {
            viewModel.mediaPagingVM.canPaginate && !listState.canScrollForward
        }
    }

    val medias = viewModel.mediaPagingVM.list
    val mediaTypes = LocalMediaTypeFilter.entries.map { stringResource(it.titleResId) }
    val season = stringResource(seasonInfo.tabTitleResId) + " " + seasonInfo.year
    val pagingListState = viewModel.mediaPagingVM.listState
    val pageNo = viewModel.mediaPagingVM.pageNo

    ActualSeasonContentCompose(
        medias = medias,
        mediaTypes = mediaTypes,
        season = season,
        pagingListState = pagingListState,
        pageNo = pageNo,
        shouldStartPaginate = shouldStartPaginate,
        listState = listState,
        coroutineScope = coroutineScope,
        onEvent = { viewModel.onEvent(it) }
    )
}

@Composable
private fun ActualSeasonContentCompose(
    medias: SnapshotStateList<LocalMedia>,
    mediaTypes: List<String>,
    season: String,
    pagingListState: PagingListState,
    pageNo: Int,
    shouldStartPaginate: Boolean,
    listState: LazyGridState,
    coroutineScope: CoroutineScope,
    onEvent: (MediaListAction) -> Unit
) {
    val isPaginationExhaust = pagingListState == PagingListState.PAGINATION_EXHAUST

    var listInitialised by remember { mutableStateOf(false) }

    if (listInitialised && shouldStartPaginate && pagingListState == PagingListState.IDLE) {
        onEvent(MediaListAction.GetNextPage)
    }

    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            ComboBox(
                items = mediaTypes,
                onSelected = { index, title ->
                    onEvent(MediaListAction.MediaTypeSelected(index))
                }
            )

            TinyOutlinedReadOnlyTextField(
                text = season,
                longestText = season,
                style = MaterialTheme.typography.bodyLarge,
                label = {
                    Text(stringResource(R.string.media_season))
                },
            )

            Box {
                var expanded by remember { mutableStateOf(false) }

                IconButton(
                    enabled = isPaginationExhaust,
                    onClick = {
                        expanded = !expanded
                    },
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.baseline_sort_24),
                        contentDescription = null,
                        tint = if (isPaginationExhaust) {
                            Color.Black
                        } else {
                            Color.LightGray
                        }
                    )
                }

                ContextMenuCompose(
                    expanded = expanded,
                    items = LocalMediaSortType.entries.map { stringResource(it.title) },
                    onItemClicked = { index ->
                        onEvent(MediaListAction.SortTypeClicked(index))
                        expanded = false
                    },
                    onDismissRequest = { expanded = false }
                )
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(all = 16.dp),
        ) {
            items(items = medias, key = { it.uniqueId }) {
                MediaListItem(it) { onEvent(MediaListAction.NavigateToDetail(it)) }
            }

            fullWidthItem(
                key = pagingListState,
            ) {
                when (pagingListState) {
                    PagingListState.LOADING -> {
                        FirstPageLoadingCompose()
                    }

                    PagingListState.PAGINATING -> {
                        PageLoadingCompose(pageNo)
                    }

                    PagingListState.PAGINATION_EXHAUST -> {
                        LastPageLoadingCompose(coroutineScope, listState)
                    }

                    else -> {}
                }
            }

            listInitialised = true
        }
    }
}

@Composable
private fun FirstPageLoadingCompose() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(R.string.loading_first_page)
        )

        CircularProgressIndicator()
    }
}

@Composable
private fun PageLoadingCompose(pageNo: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = stringResource(R.string.loading_page, pageNo))

        CircularProgressIndicator()
    }
}

@Composable
private fun LastPageLoadingCompose(coroutineScope: CoroutineScope, listState: LazyGridState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(imageVector = Icons.Rounded.Face, contentDescription = "")

        Text(text = stringResource(R.string.nothing_left))

        TextButton(
            modifier = Modifier
                .padding(top = 8.dp),
            elevation = ButtonDefaults.buttonElevation(0.dp),
            onClick = {
                coroutineScope.launch {
                    listState.animateScrollToItem(0)
                }
            },
            content = {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowUp,
                        contentDescription = ""
                    )

                    Text(text = stringResource(R.string.back_to_top))

                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowUp,
                        contentDescription = ""
                    )
                }
            }
        )
    }
}

@Suppress("unused")
@Composable
@Preview(showBackground = true)
private fun ActualSeasonContentComposePreview() {
    val medias = remember {
        mutableStateListOf(
            LocalMedia(
                malId = 4459,
                url = "https://myanimelist.net/anime/4459/Ojarumaru",
                images = LocalMedia.Images(
                    jpeg = LocalMedia.ImageUrls(
                        base = "https://cdn.myanimelist.net/images/anime/1839/132018.jpg",
                        small = "https://cdn.myanimelist.net/images/anime/1839/132018t.jpg",
                        large = "https://cdn.myanimelist.net/images/anime/1839/132018l.jpg"
                    ),
                    webp = LocalMedia.ImageUrls(
                        base = "https://cdn.myanimelist.net/images/anime/1839/132018.webp",
                        small = "https://cdn.myanimelist.net/images/anime/1839/132018t.webp",
                        large = "https://cdn.myanimelist.net/images/anime/1839/132018l.webp"
                    ),
                ),
                trailer = LocalMedia.Trailer(
                    youtubeId = null,
                    url = null,
                    embedUrl = null,
                    images = LocalMedia.ImageUrls()
                ),
                approved = true,
                titles = listOf<LocalMedia.Title>(
                    LocalMedia.Title(type = LocalMedia.Title.Type.DEFAULT, title = "Ojarumaru"),
                    LocalMedia.Title(type = LocalMedia.Title.Type.JAPANESE, title = "おじゃる丸"),
                    LocalMedia.Title(type = LocalMedia.Title.Type.ENGLISH, title = "Prince Mackaroo"),
                ),
                type = LocalMedia.Type.TV,
                source = "Original",
                episodes = null,
                status = LocalMedia.Status.CURRENTLY_AIRING,
                airing = true,
                aired = LocalMedia.Aired(
                    from = "1998-10-05T00:00:00+00:00",
                    to = null,
                    prop = LocalMedia.Aired.Prop(
                        from = LocalMedia.Aired.Date(day = 5, month = 10, year = 1998),
                        to = LocalMedia.Aired.Date(day = null, month = null, year = null)
                    ),
                    displayString = "Oct 5, 1998 to ?"
                ),
                duration = "10 min",
                rating = LocalMedia.Rating.G,
                score = 6.38f,
                scoredBy = 554,
                rank = 7785,
                popularity = 11543,
                members = 2223,
                favorites = 5,
                synopsis = "In the Heian era, around 1000 years ago, a young boy of noble family named Ojarumaru...",
                background = "The second season that aired in 1999 (episodes 91-180) was awarded the Excellence...",
                season = LocalMedia.Season.FALL,
                year = 1998,
                broadcast = LocalMedia.Broadcast(
                    day = null,
                    time = null,
                    timeZone = null,
                    displayString = "Not scheduled once per week"
                ),
                producers = listOf<LocalMedia.Entity>(
                    LocalMedia.Entity(
                        malId = 111,
                        type = "anime",
                        name = "NHK",
                        url = "https://myanimelist.net/anime/producer/111/NHK"
                    )
                ),
                licensors = listOf<LocalMedia.Entity>(
                    LocalMedia.Entity(
                        malId = 311,
                        type = "anime",
                        name = "Enoki Films",
                        url = "https://myanimelist.net/anime/producer/311/Enoki_Films"
                    )
                ),
                studios = listOf<LocalMedia.Entity>(
                    LocalMedia.Entity(
                        malId = 36,
                        type = "anime",
                        name = "Gallop",
                        url = "https://myanimelist.net/anime/producer/36/Gallop"
                    )
                ),
                genres = listOf<LocalMedia.Entity>(
                    LocalMedia.Entity(
                        malId = 2,
                        type = "anime",
                        name = "Adventure",
                        url = "https://myanimelist.net/anime/genre/2/Adventure"
                    ),
                    LocalMedia.Entity(
                        malId = 46,
                        type = "anime",
                        name = "Award Winning",
                        url = "https://myanimelist.net/anime/genre/46/Award_Winning"
                    ),
                    LocalMedia.Entity(
                        malId = 4,
                        type = "anime",
                        name = "Comedy",
                        url = "https://myanimelist.net/anime/genre/4/Comedy"
                    ),
                    LocalMedia.Entity(
                        malId = 10,
                        type = "anime",
                        name = "Fantasy",
                        url = "https://myanimelist.net/anime/genre/10/Fantasy"
                    )
                ),
                explicitGenres = listOf<LocalMedia.Entity>(),
                themes = listOf<LocalMedia.Entity>(),
                demographics = listOf<LocalMedia.Entity>(
                    LocalMedia.Entity(
                        malId = 15,
                        type = "anime",
                        name = "Kids",
                        url = "https://myanimelist.net/anime/genre/15/Kids"
                    )
                ),
            )
        )
    }
    val mediaTypes = LocalMediaTypeFilter.entries.map { stringResource(it.titleResId) }
    val season = "Spring 2025"
    val pagingListState = PagingListState.IDLE
    val pageNo = 1
    val shouldStartPaginate = false
    val listState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()
    MediaGalleryTheme {
        ActualSeasonContentCompose(
            medias = medias,
            mediaTypes = mediaTypes,
            season = season,
            pagingListState = pagingListState,
            pageNo = pageNo,
            shouldStartPaginate = shouldStartPaginate,
            listState = listState,
            coroutineScope = coroutineScope,
            onEvent = { }
        )
    }
}

private const val INITIAL_TAB_INDEX = 1
private const val LAST_TAB_INDEX = 0
private const val CURRENT_TAB_INDEX = 1
private const val NEXT_TAB_INDEX = 2
private const val ARCHIVE_TAB_INDEX = 3
