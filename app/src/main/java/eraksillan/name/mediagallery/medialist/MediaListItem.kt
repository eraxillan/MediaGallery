package eraksillan.name.mediagallery.medialist

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import eraksillan.name.mediagallery.R
import eraksillan.name.mediagallery.local.model.LocalMedia
import eraksillan.name.mediagallery.ui.theme.MediaGalleryTheme
import kotlinx.datetime.Instant
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.MonthNames.Companion.ENGLISH_ABBREVIATED
import kotlinx.datetime.format.char
import java.net.URL
import java.util.Locale

@Composable
fun MediaListItem(data: LocalMedia, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(color = MaterialTheme.colorScheme.primary)
            ) {
                onClick()
            }
    ) {
        val displayDefaultTitle = data.titles.firstOrNull {
            it.type == LocalMedia.Title.Type.DEFAULT
        }?.title ?: ""

        val displayThemes = data.themes.joinToString(", ") { it.name }
        val displayDemographics = data.demographics.joinToString(", ") { it.name }

        var displayGenres = data.genres.joinToString(", ") { it.name }
        if (data.themes.isNotEmpty()) {
            if (displayGenres.isNotEmpty()) {
                displayGenres += ", "
            }
            displayGenres += displayThemes
        }
        if (data.demographics.isNotEmpty()) {
            if (displayGenres.isNotEmpty()) {
                displayGenres += ", "
            }
            displayGenres += displayDemographics
        }

        // Example: Mar 26, 2025
        val dateCustomFormat = DateTimeComponents.Format {
            monthName(ENGLISH_ABBREVIATED)
            char(' ')
            dayOfMonth()
            char(',')
            char(' ')
            year()
        }
        val displayStartDate = data.aired.from?.format(dateCustomFormat) ?: "N/A"

        val imageStub = painterResource(id = R.drawable.media_item_placeholder)

        Column {
            Box {
                AsyncImage(
                    model = data.images.jpeg?.base.toString(),
                    modifier = Modifier
                        .height(250.dp)
                        .border(1.dp, MaterialTheme.colorScheme.outline),
                    placeholder = imageStub,
                    error = imageStub,
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    onError = { error ->
                        Log.e("MediaGallery", error.result.toString())
                    }
                )

                PopularityBadge(
                    score = data.score,
                    members = data.members,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(8.dp)
                )

                AddToListButton(
                    onClick = { },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(8.dp)
                )
            }

            Text(text = displayDefaultTitle, maxLines = 2, overflow = TextOverflow.Ellipsis)

            if (displayGenres.isNotEmpty()) {
                Text(text = displayGenres, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }

            Text(text = displayStartDate, maxLines = 1, color = MaterialTheme.colorScheme.outline)
        }
    }
}

@Composable
private fun PopularityBadge(score: Float?, members: Int?, modifier: Modifier) {
    val scoreText = if (score != null) String.format(Locale.GERMANY, "%-,2.2f", score) else "N/A"
    val membersText =
        if (members != null) String.format(Locale.forLanguageTag("RU"), "%,d", members) else "N/A"
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(Color.Black)
            .padding(4.dp)
    ) {
        Row {
            Text(text = scoreText, color = Color.White)
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color.White,
            )
        }
        Row {
            Text(text = membersText, color = Color.White)
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.baseline_people_24),
                contentDescription = null,
                tint = Color.White,
            )
        }
    }
}

@Composable
private fun AddToListButton(onClick: () -> Unit, modifier: Modifier) {
    IconButton(
        onClick = { onClick() },
        modifier = modifier
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.baseline_playlist_add_24),
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.background(Color.White)
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun MediaListItemPreview() {
    val media = LocalMedia(
        malId = 4459,
        url = URL("https://myanimelist.net/anime/4459/Ojarumaru"),
        images = LocalMedia.Images(
            jpeg = LocalMedia.ImageUrls(
                base = URL("https://cdn.myanimelist.net/images/anime/1839/132018.jpg"),
                small = URL("https://cdn.myanimelist.net/images/anime/1839/132018t.jpg"),
                large = URL("https://cdn.myanimelist.net/images/anime/1839/132018l.jpg")
            ),
            webp = LocalMedia.ImageUrls(
                base = URL("https://cdn.myanimelist.net/images/anime/1839/132018.webp"),
                small = URL("https://cdn.myanimelist.net/images/anime/1839/132018t.webp"),
                large = URL("https://cdn.myanimelist.net/images/anime/1839/132018l.webp")
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
            from = Instant.parse("1998-10-05T00:00:00+00:00"),
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
        synopsis = "In the Heian era, around 1000 years ago, a young boy of noble family named Ojarumaru is bored with his life of privilege. Meanwhile, three demons steal the power-stick of Enma, king of demons, and then lose it. Ojarumaru finds it, and uses it to transport himself to the present time. Here, he is befriended by a young boy named Kazuma, and becomes a member of his family. As time goes on, Ojarumaru makes many new friends, while dodging the comedic efforts of the three demons, Akane, Kisuke, and Aobee, as they try to recover the stick. (from ANN)",
        background = "The second season that aired in 1999 (episodes 91-180) was awarded the Excellence Award on the 3rd Japan Media Arts Festival.",
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
                url = URL("https://myanimelist.net/anime/producer/111/NHK")
            )
        ),
        licensors = listOf<LocalMedia.Entity>(
            LocalMedia.Entity(
                malId = 311,
                type = "anime",
                name = "Enoki Films",
                url = URL("https://myanimelist.net/anime/producer/311/Enoki_Films")
            )
        ),
        studios = listOf<LocalMedia.Entity>(
            LocalMedia.Entity(
                malId = 36,
                type = "anime",
                name = "Gallop",
                url = URL("https://myanimelist.net/anime/producer/36/Gallop")
            )
        ),
        genres = listOf<LocalMedia.Entity>(
            LocalMedia.Entity(
                malId = 2,
                type = "anime",
                name = "Adventure",
                url = URL("https://myanimelist.net/anime/genre/2/Adventure")
            ),
            LocalMedia.Entity(
                malId = 46,
                type = "anime",
                name = "Award Winning",
                url = URL("https://myanimelist.net/anime/genre/46/Award_Winning")
            ),
            LocalMedia.Entity(
                malId = 4,
                type = "anime",
                name = "Comedy",
                url = URL("https://myanimelist.net/anime/genre/4/Comedy")
            ),
            LocalMedia.Entity(
                malId = 10,
                type = "anime",
                name = "Fantasy",
                url = URL("https://myanimelist.net/anime/genre/10/Fantasy")
            )
        ),
        explicitGenres = listOf<LocalMedia.Entity>(),
        themes = listOf<LocalMedia.Entity>(),
        demographics = listOf<LocalMedia.Entity>(
            LocalMedia.Entity(
                malId = 15,
                type = "anime",
                name = "Kids",
                url = URL("https://myanimelist.net/anime/genre/15/Kids")
            )
        ),
    )

    MediaGalleryTheme {
        MediaListItem(media) { }
    }
}
