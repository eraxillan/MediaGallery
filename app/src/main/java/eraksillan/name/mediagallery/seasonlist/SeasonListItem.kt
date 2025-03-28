package eraksillan.name.mediagallery.seasonlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eraksillan.name.mediagallery.R
import eraksillan.name.mediagallery.local.model.LocalMedia
import eraksillan.name.mediagallery.local.utils.currentYear
import eraksillan.name.mediagallery.local.utils.getRemainingSeason
import eraksillan.name.mediagallery.ui.theme.MediaGalleryTheme

@Composable
fun SeasonListItem(year: Int, seasons: List<String>, onEvent: (SeasonListAction) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        val laterLabel = stringResource(R.string.later)

        if (year <= currentYear()) {
            Text(text = year.toString(), modifier = Modifier.align(Alignment.CenterHorizontally), textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(8.dp))
        }

        SingleChoiceSegmentedButtonRow {
            seasons.forEachIndexed { index, label ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = seasons.size
                    ),
                    onClick = {
                        val (year, season) = if (label == laterLabel) {
                            year - 1 to getRemainingSeason()
                        } else {
                            year to LocalMedia.Season.valueOf(label.uppercase())
                        }
                        onEvent(SeasonListAction.NavigateToSeason(year, season))
                    },
                    selected = false,
                    label = { Text(label) }
                )
            }
        }
    }
}

@Suppress("unused")
@Composable
@Preview(showBackground = true)
private fun SeasonListItemPreview() {
    MediaGalleryTheme {
        SeasonListItem(year = 2025, seasons = listOf("Winter", "Spring", "Summer")) { }
    }
}
