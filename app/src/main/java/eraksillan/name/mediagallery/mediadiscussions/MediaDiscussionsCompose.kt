package eraksillan.name.mediagallery.mediadiscussions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import eraksillan.name.mediagallery.R
import eraksillan.name.mediagallery.local.model.LocalMedia
import eraksillan.name.mediagallery.local.utils.formatReviewTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaDiscussionsCompose(data: List<LocalMedia.Discussion>, viewModel: MediaDiscussionsViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = stringResource(R.string.discussions),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { viewModel.onEvent(MediaDiscussionsAction.NavigateBackClicked) }) {
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
        MediaDiscussionsContentCompose(
            data = data,
            onEvent = { viewModel.onEvent(it) },
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding())
        )
    }
}

@Composable
private fun MediaDiscussionsContentCompose(
    data: List<LocalMedia.Discussion>,
    onEvent: (MediaDiscussionsAction) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = modifier.padding(16.dp)) {
        items(data) { discussion ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { onEvent(MediaDiscussionsAction.DiscussionClicked(discussion.malId)) })
            ) {
                Text(
                    text = discussion.title.orEmpty(),
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = stringResource(R.string.replies_count, discussion.comments ?: 0),
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = stringResource(
                        R.string.discussion_description_detail,
                        formatReviewTime(discussion.date),
                        discussion.authorUserName.orEmpty()
                    ),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}
