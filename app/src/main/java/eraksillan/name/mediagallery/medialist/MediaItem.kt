package eraksillan.name.mediagallery.medialist

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import eraksillan.name.mediagallery.R
import eraksillan.name.mediagallery.local.model.LocalMedia
import androidx.compose.material3.Text
import coil3.compose.AsyncImage

@Composable
fun MediaItem(data: LocalMedia) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            //.background(color = setBackgroundColor(model.merchantId == selectedMerchantId))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(color = MaterialTheme.colorScheme.primary)
            ) {
                //
            }
    ) {
        val displayDefaultTitle =
            data.titles.firstOrNull { it.type == LocalMedia.Title.Type.DEFAULT }?.title ?: ""
        val displayEnglishTitle: String =
            data.titles.firstOrNull { it.type == LocalMedia.Title.Type.ENGLISH }?.title ?: ""

        val displayStatus = when (data.status) {
            LocalMedia.Status.FINISHED_AIRING -> "Finished Airing"
            LocalMedia.Status.CURRENTLY_AIRING -> "Currently Airing"
            LocalMedia.Status.NOT_YET_AIRED -> "Not Yet Aired"
            else -> "Airing Status Unknown"
        }

        val displaySeason = when (data.season) {
            LocalMedia.Season.WINTER -> "Winter"
            LocalMedia.Season.SPRING -> "Spring"
            LocalMedia.Season.SUMMER -> "Summer"
            LocalMedia.Season.FALL -> "Fall"
            else -> "Season Unknown"
        } + " " + data.year

        val displayEpisodes =
            if (data.episodes != null) "${data.episodes} episodes" else "? episodes"

        val displayScore = data.score.toString()
        val displayScoredBy = data.scoredBy.toString() + " users"

        val imageStub = painterResource(id = R.drawable.ic_launcher_background)

        Column {
            AsyncImage(
                model = data.images.jpeg?.base.toString(),
                modifier = Modifier
                    .size(120.dp)
                    .padding(end = 4.dp)
                    .border(1.dp, MaterialTheme.colorScheme.outline),
                placeholder = imageStub,
                error = imageStub,
                contentDescription = null,
                onError = { error ->
                    Log.e("FIXME", error.result.toString())
                }
            )
            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(size = 16.dp)
                    )
                    .background(
                        color = MaterialTheme.colorScheme.surfaceContainer,
                        shape = RoundedCornerShape(size = 16.dp)
                    )
                    .padding(8.dp)
            ) {
                Text(text = displayStatus)
            }
            Text(text = displaySeason)
            Text(text = displayEpisodes)
            Text(text = displayDefaultTitle)
            Text(text = displayEnglishTitle)
            // Score
            Text(text = displayScore)
            Text(text = displayScoredBy)
            // Ranking
            //Text(text =)
            // Number of users

            // Genres

            // https://cdn.myanimelist.net/images/anime/12/43369l.jpg
            // https://cdn.myanimelist.net/images/anime/1839/132018.jpg
        }
    }
}
