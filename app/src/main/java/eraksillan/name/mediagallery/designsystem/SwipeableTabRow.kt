package eraksillan.name.mediagallery.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
fun SwipeableTabRow(
    tabTitles: List<String>,
    initialPageIndex: Int,
    pageContent: @Composable (PagerScope.(Int) -> Unit),
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(initialPage = initialPageIndex) { tabTitles.size }
    val coroutineScope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = modifier
    ) {
        tabTitles.forEachIndexed { index, title ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    coroutineScope.launch { pagerState.animateScrollToPage(index) }
                },
                text = {
                    Text(
                        text = title,
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
        pageContent(index)
    }
}
