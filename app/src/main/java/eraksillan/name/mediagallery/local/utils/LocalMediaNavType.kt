package eraksillan.name.mediagallery.local.utils

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import eraksillan.name.mediagallery.local.model.LocalMedia
import kotlinx.serialization.json.Json

val LocalMediaNavType = object : NavType<LocalMedia>(
    isNullableAllowed = false
) {
    override fun get(bundle: Bundle, key: String): LocalMedia? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, LocalMedia::class.java)
        } else {
            @Suppress("DEPRECATION")
            bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): LocalMedia {
        return Json.decodeFromString<LocalMedia>(value)
    }

    override fun serializeAsValue(value: LocalMedia): String {
        return Uri.encode(Json.encodeToString(value))
    }

    override fun put(bundle: Bundle, key: String, value: LocalMedia) {
        bundle.putParcelable(key, value)
    }

    override val name: String
        get() = LocalMedia::class.java.name
}
