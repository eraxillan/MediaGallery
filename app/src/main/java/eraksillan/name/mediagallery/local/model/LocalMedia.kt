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

    enum class Type(val titleResId: Int) {
        TV(R.string.tv),
        OVA(R.string.ova),
        MOVIE(R.string.movie),
        SPECIAL(R.string.special),
        ONA(R.string.ona),
        MUSIC(R.string.music),
        UNKNOWN(-1),
    }

    enum class Status(val titleResId: Int) {
        FINISHED_AIRING(R.string.finished),
        CURRENTLY_AIRING(R.string.airing),
        NOT_YET_AIRED(R.string.not_yet_aired),
        UNKNOWN(-1),
    }

    enum class Season(val query: String, val titleResId: Int) {
        WINTER("winter", R.string.winter),
        SPRING("spring", R.string.spring),
        SUMMER("summer", R.string.summer),
        FALL("fall", R.string.fall),
        UNKNOWN("<unknown>", -1),
    }

    enum class Rating(val titleResId: Int) {
        /**
         * All Ages
         */
        G(R.string.rating_g),

        /**
         * Children
         */
        PG(R.string.rating_pg),

        /**
         * Teens 13 or older
         */
        PG_13(R.string.rating_pg_13),

        /**
         * 17+ (vilence & profanity)
         */
        R_17(R.string.rating_r_17),

        /**
         *  Mild Nudity
         */
        R_PLUS(R.string.rating_r_plus),

        /**
         * Hentai
         */
        R_X(R.string.rating_r_x),

        UNKNOWN(-1),
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
    data class Relation(
        val type: Type,
        val entry: List<Entity>
    ) : Parcelable {
        enum class Type(val titleResId: Int) {
            PREQUEL(R.string.prequel),
            SEQUEL(R.string.sequel),
            ADAPTATION(R.string.adaptation),
            UNKNOWN(-1),
        }
    }

    @Parcelize
    @Serializable
    data class Link(
        val name: String,
        val url: String,
    ) : Parcelable

    @Parcelize
    @Serializable
    data class Cast(
        val character: MediaCharacter,
        val role: String,
        val favorites: Int,
        val voiceActors: List<MediaVoiceActor>
    ) : Parcelable {

        @Parcelize
        @Serializable
        data class MediaCharacter(
            val malId: Int,
            val url: String,
            val images: Images,
            val name: String,
        ) : Parcelable

        @Parcelize
        @Serializable
        data class MediaVoiceActor(
            val person: Person,
            val language: String,
        ) : Parcelable {

            @Parcelize
            @Serializable
            data class Person(
                val malId: Int,
                val url: String,
                val images: Images,
                val name: String,
            ) : Parcelable
        }
    }

    @Parcelize
    @Serializable
    data class PersonData(
        val malId: Int,
        val url: String,
        val images: Images,
        val name: String,
    ) : Parcelable

    @Parcelize
    @Serializable
    data class Person(
        val person: PersonData,
        val positions: List<String>
    ) : Parcelable

    @Parcelize
    @Serializable
    data class Themes(
        val openings: List<String>,
        val endings: List<String>
    ) : Parcelable

    @Parcelize
    @Serializable
    data class ReviewUser(
        val name: String,
        val url: String,
        val images: Images?,
    ) : Parcelable

    @Parcelize
    @Serializable
    data class ReviewReactions(
        val overall: Int,
        val nice: Int,
        val loveIt: Int,
        val funny: Int,
        val confusing: Int,
        val informative: Int,
        val wellWritten: Int,
        val creative: Int,
    ) : Parcelable

    @Parcelize
    @Serializable
    data class Review(
        val user: ReviewUser,
        val malId: Int,
        val url: String,
        val type: String,
        val reactions: ReviewReactions,
        val date: String,
        val review: String,
        val score: Int,
        val tags: List<String>,
        val isSpoiler: Boolean,
        val isPreliminary: Boolean,
        val episodesWatched: Int?,
    ) : Parcelable

    @Parcelize
    @Serializable
    data class RecommendationEntry(
        val malId: Int,
        val url: String?,
        val images: Images?,
        val title: String?
    ) : Parcelable

    @Parcelize
    @Serializable
    data class Recommendation(
        val entry: RecommendationEntry,
        val url: String?,
        val votes: Int?
    ) : Parcelable

    @Parcelize
    @Serializable
    data class NewsItem(
        val malId: Int,
        val url: String,
        val title: String?,
        val date: String?,
        val authorUserName: String?,
        val authorUrl: String?,
        val forumUrl: String?,
        val images: Images?,
        val comments: Int?,
        val excerpt: String?
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
