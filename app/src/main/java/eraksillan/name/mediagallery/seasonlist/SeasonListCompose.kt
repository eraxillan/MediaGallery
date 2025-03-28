package eraksillan.name.mediagallery.seasonlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eraksillan.name.mediagallery.R
import eraksillan.name.mediagallery.local.model.LocalMedia
import eraksillan.name.mediagallery.local.model.LocalSeason
import eraksillan.name.mediagallery.local.utils.currentYear
import eraksillan.name.mediagallery.local.utils.getRemainingSeason
import eraksillan.name.mediagallery.ui.theme.MediaGalleryTheme
import java.util.Locale

@Composable
fun SeasonListCompose(viewModel: SeasonListViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        SeasonListContentCompose(
            seasons = viewModel.seasonPagingVM.list,
            onEvent = { viewModel.onEvent(it) }
        )
    }
}


@Composable
private fun SeasonListContentCompose(seasons: SnapshotStateList<LocalSeason>, onEvent: (SeasonListAction) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        LazyColumn {
            item {
                SeasonListItem(currentYear() + 1, listOf(stringResource(R.string.later))) { onEvent(it) }
            }
            item {
                val remainingSeason = getRemainingSeason()
                val previousSeasons = LocalMedia.Season.entries.filter { it != remainingSeason && it != LocalMedia.Season.UNKNOWN }
                val previousSeasonTitles = previousSeasons.map { stringResource(it.titleResId) }
                SeasonListItem(currentYear(), previousSeasonTitles) { onEvent(it) }
            }
            items(items = seasons, key = { it.uniqueId }) {
                if (it.year != currentYear()) {
                    SeasonListItem(
                        year = it.year,
                        seasons = it.seasons.map { season ->
                            season.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                        }
                    ) {
                        onEvent(it)
                    }
                }
            }
        }
    }
}

@Suppress("unused")
@Composable
@Preview(showBackground = true)
private fun ArchiveSeasonContentComposePreview() {
    val seasons = remember {
        mutableStateListOf(
            LocalSeason(year = 2025, seasons = listOf("Winter", "Spring", "Summer", "Fall")),
            LocalSeason(year = 2024, seasons = listOf("Winter", "Spring", "Summer", "Fall")),
        )
    }

    MediaGalleryTheme {
        SeasonListContentCompose(
            seasons = seasons,
            onEvent = { }
        )
    }
}
