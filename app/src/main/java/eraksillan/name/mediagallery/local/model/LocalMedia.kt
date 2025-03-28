package eraksillan.name.mediagallery.local.model

import android.os.Parcelable
import eraksillan.name.mediagallery.R
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import java.util.UUID

@Parcelize
@Serializable
data class LocalMedia(
    val uniqueId: String = UUID.randomUUID().toString(),
    val malId: Int,
    val url: String,
    val images: Images,
    val trailer: Trailer,
    val approved: Boolean,
    val titles: List<Title>,
    val type: Type?,
    val source: String?,
    val episodes: Int?,
    val status: Status?,
    val airing: Boolean,
    val aired: Aired,
    val duration: String?,
    val rating: Rating?,
    val score: Float?,
    val scoredBy: Int?,
    val rank: Int?,
    val popularity: Int?,
    val members: Int?,
    val favorites: Int?,
    val synopsis: String?,
    val background: String?,
    val season: Season?,
    val year: Int?,
    val broadcast: Broadcast,
    val producers: List<Entity>,
    val licensors: List<Entity>,
    val studios: List<Entity>,
    val genres: List<Entity>,
    val explicitGenres: List<Entity>,
    val themes: List<Entity>,
    val demographics: List<Entity>,
) : Parcelable {

    enum class Type {
        TV,
        OVA,
        MOVIE,
        SPECIAL,
        ONA,
        MUSIC,
        UNKNOWN,
    }

    enum class Status {
        FINISHED_AIRING,
        CURRENTLY_AIRING,
        NOT_YET_AIRED,
        UNKNOWN,
    }

    enum class Season(val query: String, val titleResId: Int) {
        WINTER("winter", R.string.winter),
        SPRING("spring", R.string.spring),
        SUMMER("summer", R.string.summer),
        FALL("fall", R.string.fall),
        UNKNOWN("<unknown>", -1),
    }

    enum class Rating {
        /**
         * All Ages
         */
        G,

        /**
         * Children
         */
        PG,

        /**
         * Teens 13 or older
         */
        PG_13,

        /**
         * 17+ (vilence & profanity)
         */
        R,

        /**
         *  Mild Nudity
         */
        R_PLUS,

        /**
         * Hentai
         */
        R_X,

        UNKNOWN,
    }

    @Parcelize
    @Serializable
    data class ImageUrls(
        val base: String? = null,
        val small: String? = null,
        val medium: String? = null,
        val large: String? = null,
        val extraLarge: String? = null,
    ) : Parcelable

    @Parcelize
    @Serializable
    data class Images(
        val jpeg: ImageUrls? = null,
        val webp: ImageUrls? = null,
    ) : Parcelable

    @Parcelize
    @Serializable
    data class Trailer(
        val youtubeId: String?,
        val url: String?,
        val embedUrl: String?,
        val images: ImageUrls?,
    ) : Parcelable

    @Parcelize
    @Serializable
    data class Title(
        /**
         * Title type: "Default", "Japanese", "English" or "Synonym"
         */
        val type: Type,
        /**
         * Title value
         */
        val title: String,
    ) : Parcelable {
        enum class Type {
            DEFAULT,
            JAPANESE,
            ENGLISH,
            SYNONYM,
            UNKNOWN,
        }
    }

    @Parcelize
    @Serializable
    data class Aired(
        val from: String?,
        val to: String?,
        val prop: Prop,
        val displayString: String
    ) : Parcelable {

        @Parcelize
        @Serializable
        data class Date(
            val day: Int?,
            val month: Int?,
            val year: Int?,
        ) : Parcelable

        @Parcelize
        @Serializable
        data class Prop(
            val from: Date,
            val to: Date,
        ) : Parcelable
    }

    @Parcelize
    @Serializable
    data class Broadcast(
        val day: String?,
        val time: String?,
        val timeZone: String?,
        val displayString: String?,
    ) : Parcelable

    @Parcelize
    @Serializable
    data class Entity(
        val malId: Int,
        val type: String,
        val name: String,
        val url: String,
    ) : Parcelable
}
