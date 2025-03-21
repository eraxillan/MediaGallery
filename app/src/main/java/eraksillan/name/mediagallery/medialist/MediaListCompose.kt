package eraksillan.name.mediagallery.medialist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import eraksillan.name.mediagallery.paging.PagingListState

@Composable
fun MediaListCompose(viewModel: MediaListViewModel) {
    val listState = rememberLazyGridState()

    // https://medium.com/androiddevelopers/jetpack-compose-when-should-i-use-derivedstateof-63ce7954c11b
    val shouldStartPaginate by remember {
        derivedStateOf {
            viewModel.canPaginate && (listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -9) >= (listState.layoutInfo.totalItemsCount - 6)
        }
    }

    val medias = viewModel.list

    if (shouldStartPaginate && viewModel.listState == PagingListState.IDLE) {
        viewModel.getPageData()
    }

    Scaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .imePadding(),
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            )
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                state = listState,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(all = 16.dp),
            ) {
                items(items = medias, key = { it.uniqueId }) {
                    MediaItem(it)
                }

                fullWidthItem(
                    key = viewModel.listState,
                ) {
                    when (viewModel.listState) {
                        PagingListState.LOADING -> {
                            //Loading()
                            CircularProgressIndicator(color = Color.Red)
                        }

                        PagingListState.PAGINATING -> {
                            //PaginationLoading()
                            CircularProgressIndicator(color = Color.Yellow)
                        }

                        PagingListState.PAGINATION_EXHAUST -> {
                            //PaginationExhaust()
                            //CircularProgressIndicator(color = Color.Green)
                            Text(text = "Nothing left")
                        }

                        else -> {}
                    }
                }
            }

            Text("MediaList")
            Button(onClick = { viewModel.navigateToDetail() }) { Text("Button") }
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
