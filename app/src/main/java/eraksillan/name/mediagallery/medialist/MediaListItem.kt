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
import eraksillan.name.mediagallery.local.utils.mockMedia
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
    MediaGalleryTheme {
        MediaListItem(mockMedia) { }
    }
}
