package eraksillan.name.mediagallery.mediadetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MediaDetailCompose(viewModel: MediaDetailViewModel) {
    Scaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .imePadding(),
    ) { paddingValues ->
        Column(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
            Text("MediaDetail")
        }
    }
}
