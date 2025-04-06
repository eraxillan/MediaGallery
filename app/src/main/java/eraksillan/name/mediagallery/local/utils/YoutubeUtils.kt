package eraksillan.name.mediagallery.local.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

fun Context.watchYoutube(id: String) {
    val appIntent = Intent(Intent.ACTION_VIEW, "vnd.youtube:$id".toUri())
    val webIntent = Intent(
        Intent.ACTION_VIEW,
        "https://youtu.be/$id".toUri()
    )
    try {
        this.startActivity(appIntent)
    } catch (_: ActivityNotFoundException) {
        this.startActivity(webIntent)
    }
}
