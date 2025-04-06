package eraksillan.name.mediagallery.mediaextendeddetail

import androidx.compose.ui.platform.UriHandler

sealed class MediaExtendedDetailAction {
    data object NavigateBackClicked : MediaExtendedDetailAction()
    class ProducerClicked(val url: String, val uriHandler: UriHandler) : MediaExtendedDetailAction()
    class ExternalLinkClicked(val url: String, val uriHandler: UriHandler) : MediaExtendedDetailAction()
}
