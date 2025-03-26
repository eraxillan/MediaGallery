package eraksillan.name.mediagallery.medialist

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import eraksillan.name.mediagallery.R
import eraksillan.name.mediagallery.local.model.LocalMediaSortType
import eraksillan.name.mediagallery.local.model.LocalMediaTypeFilter
import eraksillan.name.mediagallery.local.utils.MediaSeasonInfo
import eraksillan.name.mediagallery.local.utils.getSeasonTriple
import eraksillan.name.mediagallery.paging.PagingListState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MediaListCompose(viewModels: List<MediaListViewModel>) {
    val pagerState = rememberPagerState(initialPage = 1) { TAB_PAGE_COUNT }
    val coroutineScope = rememberCoroutineScope()

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
            TabRow(
                selectedTabIndex = pagerState.currentPage
            ) {
                tabTitles.forEachIndexed { index, titleResId ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch { pagerState.animateScrollToPage(index) }
                        },
                        text = {
                            Text(
                                text = stringResource(titleResId),
                                style = MaterialTheme.typography.labelLarge
                            )
                        },
                        unselectedContentColor = MaterialTheme.colorScheme.secondary,
                    )
                }
            }
            HorizontalPager(
                modifier = Modifier.background(MaterialTheme.colorScheme.background),
                state = pagerState,
                verticalAlignment = Alignment.Top
            ) { index ->
                when (index) {
                    LAST_TAB_INDEX, CURRENT_TAB_INDEX, NEXT_TAB_INDEX -> {
                        ActualSeasonCompose(viewModels[index], seasonTriple.data[index])
                    }

                    ARCHIVE_TAB_INDEX -> {
                        ArchiveSeasonCompose()
                    }
                }
            }
        }
    }
}

@Composable
private fun ActualSeasonCompose(
    viewModel: MediaListViewModel,
    seasonInfo: MediaSeasonInfo
) {
    // val state by viewModel.state.collectAsState()

    val listState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()

    val shouldStartPaginate by remember {
        derivedStateOf {
            viewModel.canPaginate && !listState.canScrollForward
        }
    }

    val medias = viewModel.list
    val mediaTypes = LocalMediaTypeFilter.entries.map { stringResource(it.titleResId) }
    val season = stringResource(seasonInfo.tabTitleResId) + " " + seasonInfo.year

    var listInitialised by remember { mutableStateOf(false) }

    if (listInitialised && shouldStartPaginate && viewModel.listState == PagingListState.IDLE) {
        viewModel.getPageData()
    }

    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            ComboBox(mediaTypes) { index, title ->
                viewModel.onEvent(MediaListAction.MediaTypeSelected(index))
            }

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
                    enabled = viewModel.listState == PagingListState.PAGINATION_EXHAUST,
                    onClick = {
                        expanded = !expanded
                    },
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.baseline_sort_24),
                        contentDescription = null,
                        tint = if (viewModel.listState == PagingListState.PAGINATION_EXHAUST) {
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
                        viewModel.onEvent(MediaListAction.SortTypeClicked(index))
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
                MediaListItem(it) { viewModel.navigateToDetail() }
            }

            fullWidthItem(
                key = viewModel.listState,
            ) {
                when (viewModel.listState) {
                    PagingListState.LOADING -> {
                        FirstPageLoadingCompose()
                    }

                    PagingListState.PAGINATING -> {
                        PageLoadingCompose(viewModel.pageNo)
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
private fun ArchiveSeasonCompose() {
    // TODO: implement
    Box(modifier = Modifier.fillMaxSize())
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ComboBox(items: List<String>, onSelected: (Int, String) -> Unit) {
    val textFieldState = rememberTextFieldState(items[0])

    var expanded by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf(items[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        TinyOutlinedReadOnlyTextField(
            text = text,
            longestText = stringResource(R.string.tv_continued),
            style = MaterialTheme.typography.bodyLarge,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            label = {
                Text(stringResource(R.string.media_type))
            },
            modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable)
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            items.forEachIndexed { index, title ->
                DropdownMenuItem(
                    text = { Text(title, style = MaterialTheme.typography.bodyLarge) },
                    onClick = {
                        text = title
                        textFieldState.setTextAndPlaceCursorAtEnd(title)
                        expanded = false

                        onSelected(index, title)
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TinyOutlinedReadOnlyTextField(
    text: String,
    longestText: String,
    style: TextStyle,
    modifier: Modifier = Modifier,
    trailingIcon: @Composable (() -> Unit)? = null,
    label: @Composable (() -> Unit)? = null
) {
    val measurer = rememberTextMeasurer()
    val density = LocalDensity.current

    // https://stackoverflow.com/a/77983232/1794089
    val textResult = measurer.measure(
        text = longestText,
        style = style
    )

    var maxComboBoxWidth = with(density) {
        textResult.size.width.toDp()
    }
    // Add start and end paddings (16dp each)
    maxComboBoxWidth += 32.dp
    // Add icon with padding if present
    if (trailingIcon != null) {
        maxComboBoxWidth += 32.dp
        // Crutch
        maxComboBoxWidth += 4.dp
    }

    OutlinedTextField(
        textStyle = style,
        value = text,
        onValueChange = { },
        readOnly = true,
        singleLine = true,
        label = label,
        trailingIcon = trailingIcon,
        modifier = modifier.width(maxComboBoxWidth)
    )
}

@Composable
private fun ContextMenuCompose(
    expanded: Boolean,
    items: List<String>,
    onItemClicked: (Int) -> Unit,
    onDismissRequest: () -> Unit
) {
    DropdownMenu(
        offset = DpOffset(0.dp, 16.dp),
        expanded = expanded,
        onDismissRequest = onDismissRequest
    ) {
        items.forEachIndexed { index, title ->
            DropdownMenuItem(
                text = { Text(title) },
                onClick = { onItemClicked(index) }
            )
        }
    }
}

/**
 * An item that occupies the entire width.
 */
fun LazyGridScope.fullWidthItem(
    key: Any? = null,
    contentType: Any? = null,
    content: @Composable LazyGridItemScope.() -> Unit
) = item(
    span = { GridItemSpan(this.maxLineSpan) },
    key = key,
    contentType = contentType,
    content = content
)

private const val TAB_PAGE_COUNT = 4
private const val LAST_TAB_INDEX = 0
private const val CURRENT_TAB_INDEX = 1
private const val NEXT_TAB_INDEX = 2
private const val ARCHIVE_TAB_INDEX = 3
