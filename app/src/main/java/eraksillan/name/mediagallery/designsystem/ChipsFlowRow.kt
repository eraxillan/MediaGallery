package eraksillan.name.mediagallery.designsystem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eraksillan.name.mediagallery.ui.theme.MediaGalleryTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChipsFlowRow(data: List<String>, onClick: (Int) -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        FlowRow(
            Modifier
                .fillMaxWidth(1f)
                .wrapContentHeight(align = Alignment.Top),
            horizontalArrangement = Arrangement.Center,
        ) {
            data.forEachIndexed { index, element ->
                AssistChip(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .align(alignment = Alignment.CenterVertically),
                    onClick = { onClick(index) },
                    label = { Text(element) }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun ChipsFlowRowPreview() {
    MediaGalleryTheme {
        ChipsFlowRow(
            listOf("Aniplex", "Crunchyroll", "Sonilude", "Netmarble", "Kakao piccoma", "D&C Media"),
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
