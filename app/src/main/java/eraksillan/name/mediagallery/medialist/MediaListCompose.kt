package eraksillan.name.mediagallery.medialist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MediaListCompose(viewModel: MediaListViewModel) {
    Scaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .imePadding(),
    ) { paddingValues ->
        Column(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
            Text("MediaList")
            Button(onClick = { viewModel.navigateToDetail() }) { Text("Button") }
        }
    }
}
