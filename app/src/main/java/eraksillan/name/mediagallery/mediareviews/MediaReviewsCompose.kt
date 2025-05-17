package eraksillan.name.mediagallery.mediareviews

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import eraksillan.name.mediagallery.R
import eraksillan.name.mediagallery.designsystem.ExpandableText
import eraksillan.name.mediagallery.local.model.LocalMedia
import eraksillan.name.mediagallery.local.utils.formatReviewTime
import eraksillan.name.mediagallery.local.utils.mockReviews
import eraksillan.name.mediagallery.ui.theme.MediaGalleryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaReviewsCompose(data: List<LocalMedia.Review>, viewModel: MediaReviewsViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = stringResource(R.string.reviews),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { viewModel.onEvent(MediaReviewsAction.NavigateBackClicked) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
            )
        },
        modifier = Modifier
            .navigationBarsPadding()
            .imePadding(),
    ) { paddingValues ->
        MediaReviewsContentCompose(
            data = data,
            onEvent = { viewModel.onEvent(it) },
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding())
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MediaReviewsContentCompose(
    data: List<LocalMedia.Review>,
    onEvent: (MediaReviewsAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val imageStub = painterResource(id = R.drawable.media_item_placeholder)
    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = modifier.padding(16.dp)) {
        items(data) { review ->
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    AsyncImage(
                        model = review.user.images?.jpeg?.base,
                        modifier = Modifier
                            .width(64.dp)
                            .height(64.dp)
                            .clickable { onEvent(MediaReviewsAction.ReviewClicked(review.malId)) },
                        placeholder = imageStub,
                        error = imageStub,
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        onError = { error ->
                            Log.e("MediaGallery", error.result.toString())
                        }
                    )

                    Column {
                        Text(text = review.user.name)
                        FlowRow(
                            Modifier
                                .fillMaxWidth(1f)
                                .wrapContentHeight(align = Alignment.Top),
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            review.tags.forEachIndexed { index, tag ->
                                MediaReviewsContentTagCompose(tag)
                            }
                        }
                    }
                }

                ExpandableText(
                    text = review.review,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    collapsedMaxLine = 4
                )

                Box(modifier = Modifier.fillMaxWidth()) {
                    // Example: Jan 18, 8:28 AM
                    Text(text = formatReviewTime(review.date), modifier = Modifier.align(Alignment.TopStart))
                    Text(
                        text = "\uD83D\uDE02\uD83D\uDC4D\uD83E\uDD28 ${review.reactions.overall}",
                        modifier = Modifier.align(Alignment.TopEnd)
                    )
                }
            }
        }
    }
}

@Composable
private fun MediaReviewsContentTagCompose(tag: String) {
    val backgroundColor = if (tag == "Preliminary") Color.Transparent else Color.LightGray
    Box(modifier = Modifier.background(backgroundColor)) {
        var text = tag
        var textColor = Color.Black
        Row(verticalAlignment = Alignment.CenterVertically) {
            when (tag) {
                "Recommended" -> {
                    textColor = Color.Blue
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.baseline_star_24),
                        contentDescription = null,
                        tint = Color.Blue,
                    )
                }

                "Not Recommended" -> {
                    textColor = Color.Red
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.outline_star_outline_24),
                        contentDescription = null,
                        tint = Color.Red
                    )
                }

                "Mixed Feelings" -> {
                    textColor = Color.DarkGray
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.baseline_star_half_24),
                        contentDescription = null,
                        tint = Color.DarkGray
                    )
                }

                "Funny" -> {
                    text += "\uD83D\uDE02"
                    textColor = Color.DarkGray
                }

                "Well-written" -> {
                    text += "\uD83D\uDCDD"
                    textColor = Color.DarkGray
                }

                "Preliminary" -> {
                    textColor = Color.Green
                }
            }
            Text(text = text, color = textColor, modifier = Modifier.padding(4.dp))
        }
    }
}

@Suppress("unused")
@Preview(showBackground = true)
@Composable
private fun MediaReviewsPreview() {
    MediaGalleryTheme {
        MediaReviewsContentCompose(
            data = mockReviews,
            onEvent = { }
        )
    }
}
