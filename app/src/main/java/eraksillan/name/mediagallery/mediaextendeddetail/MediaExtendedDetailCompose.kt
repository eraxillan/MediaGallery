package eraksillan.name.mediagallery.mediaextendeddetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eraksillan.name.mediagallery.R
import eraksillan.name.mediagallery.designsystem.ChipsFlowRow
import eraksillan.name.mediagallery.local.model.LocalMedia
import eraksillan.name.mediagallery.local.utils.japaneseTitle
import eraksillan.name.mediagallery.local.utils.mockExternalLinks
import eraksillan.name.mediagallery.local.utils.mockMedia
import eraksillan.name.mediagallery.local.utils.mockMoreInfo
import eraksillan.name.mediagallery.local.utils.producers
import eraksillan.name.mediagallery.local.utils.synonyms
import eraksillan.name.mediagallery.ui.theme.MediaGalleryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaExtendedDetailCompose(data: LocalMedia, viewModel: MediaExtendedDetailViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Row {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                        )
                        Text(
                            text = stringResource(R.string.media_detail),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { viewModel.onEvent(MediaExtendedDetailAction.NavigateBackClicked) }) {
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
        MediaExtendedDetailContentCompose(
            data,
            viewModel.externalLinksPagingVM.list,
            viewModel.moreInfoPagingVM.list.firstOrNull(),
            Modifier
                .fillMaxWidth()
                .padding(top = paddingValues.calculateTopPadding(), start = 16.dp, end = 16.dp)
        ) { viewModel.onEvent(it) }
    }
}

@Composable
fun MediaExtendedDetailContentCompose(
    data: LocalMedia,
    externalLinks: List<LocalMedia.Link>,
    moreInfo: String?,
    modifier: Modifier = Modifier,
    onEvent: (MediaExtendedDetailAction) -> Unit
) {
    val unknownString = stringResource(R.string.unknown)
    val uriHandler = LocalUriHandler.current
    Column(modifier) {
        Text(
            text = stringResource(R.string.japanese),
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(text = data.japaneseTitle() ?: unknownString)

        Text(
            text = stringResource(R.string.synonyms),
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(text = data.synonyms().joinToString(","))

        Text(
            text = stringResource(R.string.producers),
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.padding(top = 8.dp)
        )
        ChipsFlowRow(
            data.producers(),
            onClick = { index ->
                onEvent(MediaExtendedDetailAction.ProducerClicked(data.producers[index].url, uriHandler))
            },
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = stringResource(R.string.external_links),
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.padding(top = 8.dp)
        )
        ChipsFlowRow(
            externalLinks.map { it.name },
            onClick = { index ->
                onEvent(MediaExtendedDetailAction.ExternalLinkClicked(externalLinks[index].url, uriHandler))
            },
            modifier = Modifier.fillMaxWidth()
        )

        Text(text = moreInfo ?: unknownString, modifier = Modifier.padding(top = 8.dp))
    }
}

@Suppress("unused")
@Composable
@Preview(showBackground = true)
private fun MediaExtendedDetailComposePreview() {
    MediaGalleryTheme {
        MediaExtendedDetailContentCompose(
            mockMedia,
            mockExternalLinks,
            mockMoreInfo,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        ) { }
    }
}
