package eraksillan.name.mediagallery.designsystem

import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import eraksillan.name.mediagallery.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComboBox(items: List<String>, onSelected: (Int, String) -> Unit) {
    val textFieldState = rememberTextFieldState(items[0])

    var expanded by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf(items[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        TinyOutlinedReadOnlyTextField(
            text = text,
            longestText = stringResource(R.string.tv_continued),
            style = MaterialTheme.typography.bodyLarge,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            label = {
                Text(stringResource(R.string.media_type))
            },
            modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable)
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            items.forEachIndexed { index, title ->
                DropdownMenuItem(
                    text = { Text(title, style = MaterialTheme.typography.bodyLarge) },
                    onClick = {
                        text = title
                        textFieldState.setTextAndPlaceCursorAtEnd(title)
                        expanded = false

                        onSelected(index, title)
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}
