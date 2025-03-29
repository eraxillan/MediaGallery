package eraksillan.name.mediagallery.local.utils

import android.app.Activity
import android.content.Intent
import androidx.core.app.ShareCompat

/**
 * Helper function for calling a share functionality.
 * Should be used when user presses a share button/menu item
 */
fun createShareIntent(activity: Activity, text: String) {
    val shareIntent = ShareCompat.IntentBuilder(activity)
        .setText(text)
        .setType("text/plain")
        .createChooserIntent()
        .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
    activity.startActivity(shareIntent)
}
