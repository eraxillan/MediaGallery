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
import eraksillan.name.mediagallery.local.utils.displayDefaultTitle
import eraksillan.name.mediagallery.local.utils.displayGenresText
import eraksillan.name.mediagallery.local.utils.displayStartDateText
import eraksillan.name.mediagallery.local.utils.membersDisplayText
import eraksillan.name.mediagallery.local.utils.scoreDisplayText
import eraksillan.name.mediagallery.ui.theme.MediaGalleryTheme

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
        Column {
            Box {
                val imageStub = painterResource(id = R.drawable.media_item_placeholder)
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
                    data = data,
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

            Text(text = data.displayDefaultTitle(), maxLines = 2, overflow = TextOverflow.Ellipsis)

            if (data.displayGenresText().isNotEmpty()) {
                Text(text = data.displayGenresText(), maxLines = 1, overflow = TextOverflow.Ellipsis)
            }

            Text(text = data.displayStartDateText(), maxLines = 1, color = MaterialTheme.colorScheme.outline)
        }
    }
}

@Composable
private fun PopularityBadge(data: LocalMedia, modifier: Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(Color.Black)
            .padding(4.dp)
    ) {
        Row {
            Text(text = data.scoreDisplayText(), color = Color.White)
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color.White,
            )
        }
        Row {
            Text(text = data.membersDisplayText(), color = Color.White)
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
        background = "The second season that aired in 1999 (episodes 91-180) was awarded ...",
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

    MediaGalleryTheme {
        MediaListItem(media) { }
    }
}
