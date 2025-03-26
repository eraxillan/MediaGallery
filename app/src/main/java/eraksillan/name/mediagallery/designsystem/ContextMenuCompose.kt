package eraksillan.name.mediagallery.designsystem

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@Composable
fun ContextMenuCompose(
    expanded: Boolean,
    items: List<String>,
    onItemClicked: (Int) -> Unit,
    onDismissRequest: () -> Unit
) {
    DropdownMenu(
        offset = DpOffset(0.dp, 16.dp),
        expanded = expanded,
        onDismissRequest = onDismissRequest
    ) {
        items.forEachIndexed { index, title ->
            DropdownMenuItem(
                text = { Text(title) },
                onClick = { onItemClicked(index) }
            )
        }
    }
}
