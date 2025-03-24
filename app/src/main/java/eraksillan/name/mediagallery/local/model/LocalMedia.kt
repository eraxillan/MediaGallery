package eraksillan.name.mediagallery.local.model

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import java.net.URL
import java.util.UUID

data class LocalMedia(
    val uniqueId: String = UUID.randomUUID().toString(),
    val malId: Int,
    val url: URL,
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
) {

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

    enum class Season(val query: String) {
        WINTER("winter"),
        SPRING("spring"),
        SUMMER("summer"),
        FALL("fall"),
        UNKNOWN("<unknown>"),
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

    data class ImageUrls(
        val base: URL? = null,
        val small: URL? = null,
        val medium: URL? = null,
        val large: URL? = null,
        val extraLarge: URL? = null,
    )

    data class Images(
        val jpeg: ImageUrls? = null,
        val webp: ImageUrls? = null,
    )

    data class Trailer(
        val youtubeId: String?,
        val url: URL?,
        val embedUrl: URL?,
        val images: ImageUrls?,
    )

    data class Title(
        /**
         * Title type: "Default", "Japanese", "English" or "Synonym"
         */
        val type: Type,
        /**
         * Title value
         */
        val title: String,
    ) {
        enum class Type {
            DEFAULT,
            JAPANESE,
            ENGLISH,
            SYNONYM,
            UNKNOWN,
        }
    }

    data class Aired(
        val from: Instant?,
        val to: Instant?,
        val prop: Prop,
        val displayString: String
    ) {
        data class Date(
            val day: Int?,
            val month: Int?,
            val year: Int?,
        )

        data class Prop(
            val from: Date,
            val to: Date,
        )
    }

    data class Broadcast(
        val day: String?,
        val time: LocalTime?,
        val timeZone: TimeZone?,
        val displayString: String?,
    )

    data class Entity(
        val malId: Int,
        val type: String,
        val name: String,
        val url: URL,
    )
}
