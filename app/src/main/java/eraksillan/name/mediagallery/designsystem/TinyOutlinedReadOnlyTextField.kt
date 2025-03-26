package eraksillan.name.mediagallery.designsystem

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eraksillan.name.mediagallery.ui.theme.MediaGalleryTheme

@Composable
fun TinyOutlinedReadOnlyTextField(
    text: String,
    longestText: String,
    style: TextStyle,
    modifier: Modifier = Modifier,
    trailingIcon: @Composable (() -> Unit)? = null,
    label: @Composable (() -> Unit)? = null
) {
    val measurer = rememberTextMeasurer()
    val density = LocalDensity.current

    // https://stackoverflow.com/a/77983232/1794089
    val textResult = measurer.measure(
        text = longestText,
        style = style
    )

    var maxComboBoxWidth = with(density) {
        textResult.size.width.toDp()
    }
    // Add start and end paddings (16dp each)
    maxComboBoxWidth += 32.dp
    // Add icon with padding if present
    if (trailingIcon != null) {
        maxComboBoxWidth += 32.dp
        // Crutch
        maxComboBoxWidth += 4.dp
    }

    OutlinedTextField(
        textStyle = style,
        value = text,
        onValueChange = { },
        readOnly = true,
        singleLine = true,
        label = label,
        trailingIcon = trailingIcon,
        modifier = modifier.width(maxComboBoxWidth)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun TinyOutlinedReadOnlyTextFieldPreview() {
    MediaGalleryTheme {
        TinyOutlinedReadOnlyTextField(
            text = "Test text",
            longestText = "Test text",
            style = MaterialTheme.typography.bodyLarge,
            label = {
                Text("Test label")
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = false)
            },
            modifier = Modifier.padding(16.dp)
        )
    }
}
