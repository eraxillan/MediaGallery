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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import eraksillan.name.mediagallery.R
import eraksillan.name.mediagallery.paging.PagingListState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaListCompose(viewModel: MediaListViewModel) {
    val pagerState = rememberPagerState { 4 }
    val coroutineScope = rememberCoroutineScope()

    val seasonTriple = viewModel.getSeasonTriple() ?: return
    val tabs = listOf(
        seasonTriple.last.tabTitleResId,
        seasonTriple.current.tabTitleResId,
        seasonTriple.next.tabTitleResId,
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
            PrimaryTabRow(
                selectedTabIndex = pagerState.currentPage
            ) {
                tabs.forEachIndexed { index, titleResId ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                        text = { Text(stringResource(titleResId)) },
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
                    0, 1, 2 -> ActualSeasonCompose(viewModel)
                    3 -> ArchiveSeasonCompose(viewModel)
                }
            }
        }
    }
}

@Composable
private fun ActualSeasonCompose(viewModel: MediaListViewModel) {
    val listState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()

    // https://medium.com/androiddevelopers/jetpack-compose-when-should-i-use-derivedstateof-63ce7954c11b
    // https://stackoverflow.com/q/66712286/1794089
    val shouldStartPaginate by remember {
        derivedStateOf {
            viewModel.canPaginate && !listState.canScrollForward
        }
    }

    val medias = viewModel.list

    if (shouldStartPaginate && viewModel.listState == PagingListState.IDLE) {
        viewModel.getPageData()
    }

    Column {
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
        }
    }
}

@Composable
private fun ArchiveSeasonCompose(viewModel: MediaListViewModel) {
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
            text = "Loading first page..."
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
        Text(text = "Loading page $pageNo...")

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

        Text(text = "Nothing left.")

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

                    Text(text = "Back to Top")

                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowUp,
                        contentDescription = ""
                    )
                }
            }
        )
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
