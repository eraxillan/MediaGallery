package eraksillan.name.mediagallery.mediarecommendations

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import eraksillan.name.mediagallery.R
import eraksillan.name.mediagallery.local.model.LocalMedia

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaRecommendationsCompose(data: List<LocalMedia.Recommendation>, viewModel: MediaRecommendationsViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = stringResource(R.string.recommendations),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { viewModel.onEvent(MediaRecommendationsAction.NavigateBackClicked) }) {
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
        MediaRecommendationsContentCompose(
            data = data,
            onEvent = { viewModel.onEvent(it) },
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding())
        )
    }
}

@Composable
private fun MediaRecommendationsContentCompose(
    data: List<LocalMedia.Recommendation>,
    onEvent: (MediaRecommendationsAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val imageStub = painterResource(id = R.drawable.media_item_placeholder)
    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = modifier.padding(16.dp)) {
        items(data) { recommendation ->
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    AsyncImage(
                        model = recommendation.entry.images?.jpeg?.base,
                        modifier = Modifier
                            .width(64.dp)
                            .height(64.dp)
                            .clickable { onEvent(MediaRecommendationsAction.RecommendationClicked(recommendation.entry.malId)) },
                        placeholder = imageStub,
                        error = imageStub,
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        onError = { error ->
                            Log.e("MediaGallery", error.result.toString())
                        }
                    )

                    Column {
                        Text(
                            text = recommendation.entry.title.orEmpty(),
                            style = MaterialTheme.typography.titleSmall
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = stringResource(R.string.votes_count, recommendation.votes ?: 0),
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
        }
    }
}
