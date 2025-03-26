package eraksillan.name.mediagallery.designsystem

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import eraksillan.name.mediagallery.ui.theme.MediaGalleryTheme

@Composable
fun ContextMenuCompose(
    expanded: Boolean,
    items: List<String>,
    onItemClicked: (Int) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    DropdownMenu(
        offset = DpOffset(0.dp, 16.dp),
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier
    ) {
        items.forEachIndexed { index, title ->
            DropdownMenuItem(
                text = { Text(title) },
                onClick = { onItemClicked(index) }
            )
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFDBD0)
private fun ContextMenuComposePreview() {
    MediaGalleryTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            Text("test")
            ContextMenuCompose(
                expanded = true,
                items = listOf("First", "Second", "Third"),
                onItemClicked = { },
                onDismissRequest = { },
                modifier = Modifier.padding(bottom = 50.dp)
            )
        }
    }
}
