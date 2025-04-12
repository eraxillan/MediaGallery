package eraksillan.name.mediagallery.remote.model

import eraksillan.name.mediagallery.local.model.LocalMedia
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Media(
    @SerialName("mal_id")
    val malId: Int,
    @SerialName("url")
    val url: String,
    @SerialName("images")
    val images: Images,
    @SerialName("trailer")
    val trailer: Trailer,
    @SerialName("approved")
    val approved: Boolean,
    @SerialName("titles")
    val titles: List<Title>,
    @SerialName("type")
    val type: String?,
    @SerialName("source")
    val source: String?,
    @SerialName("episodes")
    val episodes: Int?,
    @SerialName("status")
    val status: String?,
    @SerialName("airing")
    val airing: Boolean,
    @SerialName("aired")
    val aired: Aired,
    @SerialName("duration")
    val duration: String?,
    @SerialName("rating")
    val rating: String?,
    @SerialName("score")
    val score: Float?,
    @SerialName("scored_by")
    val scoredBy: Int?,
    @SerialName("rank")
    val rank: Int?,
    @SerialName("popularity")
    val popularity: Int?,
    @SerialName("members")
    val members: Int?,
    @SerialName("favorites")
    val favorites: Int?,
    @SerialName("synopsis")
    val synopsis: String?,
    @SerialName("background")
    val background: String?,
    @SerialName("season")
    val season: String?,
    @SerialName("year")
    val year: Int?,
    @SerialName("broadcast")
    val broadcast: Broadcast,
    @SerialName("producers")
    val producers: List<Entity>,
    @SerialName("licensors")
    val licensors: List<Entity>,
    @SerialName("studios")
    val studios: List<Entity>,
    @SerialName("genres")
    val genres: List<Entity>,
    @SerialName("explicit_genres")
    val explicitGenres: List<Entity>,
    @SerialName("themes")
    val themes: List<Entity>,
    @SerialName("demographics")
    val demographics: List<Entity>,
) {

    @Serializable
    data class ImageUrls(
        @SerialName("image_url")
        val base: String? = null,
        @SerialName("small_image_url")
        val small: String? = null,
        @SerialName("medium_image_url")
        val medium: String? = null,
        @SerialName("large_image_url")
        val large: String? = null,
        @SerialName("maximum_image_url")
        val extraLarge: String? = null,
    )

    @Serializable
    data class Images(
        @SerialName("jpg")
        val jpeg: ImageUrls? = null,
        @SerialName("webp")
        val webp: ImageUrls? = null,
    )

    @Serializable
    data class Trailer(
        @SerialName("youtube_id")
        val youtubeId: String?,
        @SerialName("url")
        val url: String?,
        @SerialName("embed_url")
        val embedUrl: String?,
        @SerialName("images")
        val images: ImageUrls?,
    )

    @Serializable
    data class Title(
        /**
         * Title type: "Default", "Japanese", "English" or "Synonym"
         */
        @SerialName("type")
        val type: String,
        /**
         * Title value
         */
        @SerialName("title")
        val title: String,
    )

    @Serializable
    data class Aired(
        @SerialName("from")
        val from: String?,
        @SerialName("to")
        val to: String?,
        @SerialName("prop")
        val prop: Prop,
        @SerialName("string")
        val displayString: String
    ) {
        @Serializable
        data class Date(
            val day: Int?,
            val month: Int?,
            val year: Int?,
        )

        @Serializable
        data class Prop(
            val from: Date,
            val to: Date,
        )
    }

    @Serializable
    data class Broadcast(
        @SerialName("day")
        val day: String?,
        @SerialName("time")
        val time: String?,
        @SerialName("timezone")
        val timeZone: String?,
        @SerialName("string")
        val displayString: String?,
    )

    @Serializable
    data class Relation(
        @SerialName("relation")
        val type: String,
        @SerialName("entry")
        val entry: List<Entity>
    )

    @Serializable
    data class Link(
        @SerialName("name")
        val name: String,
        @SerialName("url")
        val url: String,
    )

    @Serializable
    data class Cast(
        @SerialName("character")
        val character: MediaCharacter,
        @SerialName("role")
        val role: String,
        @SerialName("favorites")
        val favorites: Int,
        @SerialName("voice_actors")
        val voiceActors: List<MediaVoiceActor>
    ) {
        @Serializable
        data class MediaCharacter(
            @SerialName("mal_id")
            val malId: Int,
            @SerialName("url")
            val url: String,
            @SerialName("images")
            val images: Images,
            @SerialName("name")
            val name: String,
        )

        @Serializable
        data class MediaVoiceActor(
            @SerialName("person")
            val person: Person,
            @SerialName("language")
            val language: String,
        ) {
            @Serializable
            data class Person(
                @SerialName("mal_id")
                val malId: Int,
                @SerialName("url")
                val url: String,
                @SerialName("images")
                val images: Images,
                @SerialName("name")
                val name: String,
            )
        }
    }

    @Serializable
    data class Entity(
        @SerialName("mal_id")
        val malId: Int,
        @SerialName("type")
        val type: String,
        @SerialName("name")
        val name: String,
        @SerialName("url")
        val url: String,
    )
}

fun Media.ImageUrls.toLocal(): LocalMedia.ImageUrls {
    return LocalMedia.ImageUrls(
        base,
        small,
        medium,
        large,
        extraLarge
    )
}

fun Media.Images.toLocal(): LocalMedia.Images {
    return LocalMedia.Images(jpeg?.toLocal(), webp?.toLocal())
}

fun Media.Trailer.toLocal(): LocalMedia.Trailer {
    return LocalMedia.Trailer(youtubeId, url, embedUrl, images?.toLocal())
}

fun Media.Title.toLocal(): LocalMedia.Title {
    val type = when (type) {
        "Default" -> LocalMedia.Title.Type.DEFAULT
        "Japanese" -> LocalMedia.Title.Type.JAPANESE
        "English" -> LocalMedia.Title.Type.ENGLISH
        "Synonym" -> LocalMedia.Title.Type.SYNONYM
        else -> LocalMedia.Title.Type.UNKNOWN
    }
    return LocalMedia.Title(type, title)
}

fun String.toLocalType(): LocalMedia.Type {
    return when (this) {
        "TV" -> LocalMedia.Type.TV
        "OVA" -> LocalMedia.Type.OVA
        "Movie" -> LocalMedia.Type.MOVIE
        "Special" -> LocalMedia.Type.SPECIAL
        "ONA" -> LocalMedia.Type.ONA
        "Music" -> LocalMedia.Type.MUSIC
        else -> LocalMedia.Type.UNKNOWN
    }
}

fun String.toLocalStatus(): LocalMedia.Status {
    return when (this) {
        "Finished Airing" -> LocalMedia.Status.FINISHED_AIRING
        "Currently Airing" -> LocalMedia.Status.CURRENTLY_AIRING
        "Not yet aired" -> LocalMedia.Status.NOT_YET_AIRED
        else -> LocalMedia.Status.UNKNOWN
    }
}

fun Media.Aired.Date.toLocal(): LocalMedia.Aired.Date {
    return LocalMedia.Aired.Date(day, month, year)
}

fun Media.Aired.Prop.toLocal(): LocalMedia.Aired.Prop {
    return LocalMedia.Aired.Prop(from.toLocal(), to.toLocal())
}

fun Instant.Companion.parseOrNull(input: String?): Instant? {
    return if (input != null) parse(input) else null
}

fun Media.Aired.toLocal(): LocalMedia.Aired {
    return LocalMedia.Aired(
        from,
        to,
        prop.toLocal(),
        displayString
    )
}

fun String.toLocalRating(): LocalMedia.Rating {
    return when (this) {
        "G - All Ages" -> LocalMedia.Rating.G
        "PG - Children" -> LocalMedia.Rating.PG
        "PG-13 - Teens 13 or older" -> LocalMedia.Rating.PG_13
        "R - 17+ (violence & profanity)" -> LocalMedia.Rating.R_17
        "R+ - Mild Nudity" -> LocalMedia.Rating.R_PLUS
        "Rx - Hentai" -> LocalMedia.Rating.R_X
        else -> LocalMedia.Rating.UNKNOWN
    }
}

fun String.toLocalSeason(): LocalMedia.Season {
    return when (this) {
        "summer" -> LocalMedia.Season.SUMMER
        "winter" -> LocalMedia.Season.WINTER
        "spring" -> LocalMedia.Season.SPRING
        "fall" -> LocalMedia.Season.FALL
        else -> LocalMedia.Season.UNKNOWN
    }
}

fun String.toLocalRelationType(): LocalMedia.Relation.Type {
    return when (this) {
        "Prequel" -> LocalMedia.Relation.Type.PREQUEL
        "Sequel" -> LocalMedia.Relation.Type.SEQUEL
        "Adaptation" -> LocalMedia.Relation.Type.ADAPTATION
        else -> LocalMedia.Relation.Type.UNKNOWN
    }
}

fun Media.Broadcast.toLocal(): LocalMedia.Broadcast {
    return LocalMedia.Broadcast(
        day,
        time,
        timeZone,
        displayString
    )
}

fun Media.Relation.toLocal(): LocalMedia.Relation {
    return LocalMedia.Relation(type.toLocalRelationType(), entry.toLocal())
}

fun Media.Link.toLocal(): LocalMedia.Link {
    return LocalMedia.Link(name, url)
}

fun Media.Cast.MediaCharacter.toLocal(): LocalMedia.Cast.MediaCharacter {
    return LocalMedia.Cast.MediaCharacter(malId, url, images.toLocal(), name)
}

fun Media.Cast.MediaVoiceActor.Person.toLocal(): LocalMedia.Cast.MediaVoiceActor.Person {
    return LocalMedia.Cast.MediaVoiceActor.Person(malId, url, images.toLocal(), name)
}

fun Media.Cast.MediaVoiceActor.toLocal(): LocalMedia.Cast.MediaVoiceActor {
    return LocalMedia.Cast.MediaVoiceActor(person.toLocal(), language)
}

fun Media.Cast.toLocal(): LocalMedia.Cast {
    return LocalMedia.Cast(character.toLocal(), role, favorites, voiceActors.map { it.toLocal() })
}

fun Media.Entity.toLocal(): LocalMedia.Entity {
    return LocalMedia.Entity(malId, type, name, url)
}

fun List<Media.Entity>.toLocal(): List<LocalMedia.Entity> = map { it.toLocal() }

fun Media.toLocal(): LocalMedia {
    return LocalMedia(
        malId = malId,
        url = url,
        images = images.toLocal(),
        trailer = trailer.toLocal(),
        approved = approved,
        titles = titles.map { it.toLocal() },
        type = type?.toLocalType(),
        source = source,
        episodes = episodes,
        status = status?.toLocalStatus(),
        airing = airing,
        aired = aired.toLocal(),
        duration = duration,
        rating = rating?.toLocalRating(),
        score = score,
        scoredBy = scoredBy,
        rank = rank,
        popularity = popularity,
        members = members,
        favorites = favorites,
        synopsis = synopsis,
        background = background,
        season = season?.toLocalSeason(),
        year = year,
        broadcast = broadcast.toLocal(),
        producers = producers.toLocal(),
        licensors = licensors.toLocal(),
        studios = studios.toLocal(),
        genres = genres.toLocal(),
        explicitGenres = explicitGenres.toLocal(),
        themes = themes.toLocal(),
        demographics = demographics.toLocal()
    )
}
