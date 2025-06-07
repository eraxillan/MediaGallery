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
    data class PersonData(
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
    data class Person(
        @SerialName("person")
        val person: PersonData,
        @SerialName("positions")
        val positions: List<String>
    )

    @Serializable
    data class Themes(
        @SerialName("openings")
        val openings: List<String>,
        @SerialName("endings")
        val endings: List<String>
    )

    @Serializable
    data class ReviewUser(
        @SerialName("username")
        val name: String,
        @SerialName("url")
        val url: String,
        @SerialName("images")
        val images: Images?,
    )

    @Serializable
    data class ReviewReactions(
        @SerialName("overall")
        val overall: Int,
        @SerialName("nice")
        val nice: Int,
        @SerialName("love_it")
        val loveIt: Int,
        @SerialName("funny")
        val funny: Int,
        @SerialName("confusing")
        val confusing: Int,
        @SerialName("informative")
        val informative: Int,
        @SerialName("well_written")
        val wellWritten: Int,
        @SerialName("creative")
        val creative: Int,
    )

    @Serializable
    data class Review(
        @SerialName("user")
        val user: ReviewUser,
        @SerialName("mal_id")
        val malId: Int,
        @SerialName("url")
        val url: String,
        @SerialName("type")
        val type: String,
        @SerialName("reactions")
        val reactions: ReviewReactions,
        @SerialName("date")
        val date: String,
        @SerialName("review")
        val review: String,
        @SerialName("score")
        val score: Int,
        @SerialName("tags")
        val tags: List<String>,
        @SerialName("is_spoiler")
        val isSpoiler: Boolean,
        @SerialName("is_preliminary")
        val isPreliminary: Boolean,
        @SerialName("episodes_watched")
        val episodesWatched: Int?,
    )

    @Serializable
    data class RecommendationEntry(
        @SerialName("mal_id")
        val malId: Int,
        @SerialName("url")
        val url: String,
        @SerialName("images")
        val images: Images,
        @SerialName("title")
        val title: String
    )

    @Serializable
    data class Recommendation(
        @SerialName("entry")
        val entry: RecommendationEntry,
        @SerialName("url")
        val url: String,
        @SerialName("votes")
        val votes: Int
    )

    @Serializable
    data class NewsItem(
        @SerialName("mal_id")
        val malId: Int,
        @SerialName("url")
        val url: String,
        @SerialName("title")
        val title: String?,
        @SerialName("date")
        val date: String?,
        @SerialName("author_username")
        val authorUserName: String?,
        @SerialName("author_url")
        val authorUrl: String?,
        @SerialName("forum_url")
        val forumUrl: String?,
        @SerialName("images")
        val images: Images?,
        @SerialName("comments")
        val comments: Int?,
        @SerialName("excerpt")
        val excerpt: String?
    )

    @Serializable
    data class DiscussionComment(
        @SerialName("url")
        val url: String,
        @SerialName("author_username")
        val authorUserName: String?,
        @SerialName("author_url")
        val authorUrl: String?,
        @SerialName("date")
        val date: String?
    )

    @Serializable
    data class Discussion(
        @SerialName("mal_id")
        val malId: Int,
        @SerialName("url")
        val url: String,
        @SerialName("title")
        val title: String?,
        @SerialName("date")
        val date: String?,
        @SerialName("author_username")
        val authorUserName: String?,
        @SerialName("author_url")
        val authorUrl: String?,
        @SerialName("comments")
        val comments: Int?,
        @SerialName("last_comment")
        val lastComment: DiscussionComment
    )

    @Serializable
    data class StatisticsScore(
        @SerialName("score")
        val score: Int,
        @SerialName("votes")
        val votes: Int,
        @SerialName("percentage")
        val percentage: Float
    )

    @Serializable
    data class Statistics(
        @SerialName("watching")
        val watching: Int,
        @SerialName("completed")
        val completed: Int,
        @SerialName("on_hold")
        val onHold: Int,
        @SerialName("dropped")
        val dropped: Int,
        @SerialName("plan_to_watch")
        val planToWatch: Int,
        @SerialName("total")
        val total: Int,
        @SerialName("scores")
        val scores: List<StatisticsScore>
    )

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

fun Media.PersonData.toLocal(): LocalMedia.PersonData {
    return LocalMedia.PersonData(malId, url, images.toLocal(), name)
}

fun Media.Person.toLocal(): LocalMedia.Person {
    return LocalMedia.Person(person.toLocal(), positions)
}

fun Media.Themes.toLocal(): LocalMedia.Themes {
    return LocalMedia.Themes(openings, endings)
}

fun Media.ReviewUser.toLocal(): LocalMedia.ReviewUser {
    return LocalMedia.ReviewUser(name, url, images?.toLocal())
}

fun Media.ReviewReactions.toLocal(): LocalMedia.ReviewReactions {
    return LocalMedia.ReviewReactions(
        overall,
        nice,
        loveIt,
        funny,
        confusing,
        informative,
        wellWritten,
        creative
    )
}

fun Media.Review.toLocal(): LocalMedia.Review {
    return LocalMedia.Review(
        user = user.toLocal(),
        malId = malId,
        url = url,
        type = type,
        reactions = reactions.toLocal(),
        date = date,
        review = review,
        score = score,
        tags = tags,
        isSpoiler = isSpoiler,
        isPreliminary = isPreliminary,
        episodesWatched = episodesWatched
    )
}

fun Media.RecommendationEntry.toLocal(): LocalMedia.RecommendationEntry {
    return LocalMedia.RecommendationEntry(
        malId = malId,
        url = url,
        images = images.toLocal(),
        title = title
    )
}

fun Media.Recommendation.toLocal(): LocalMedia.Recommendation {
    return LocalMedia.Recommendation(
        entry.toLocal(),
        url = url,
        votes = votes
    )
}

fun Media.NewsItem.toLocal(): LocalMedia.NewsItem {
    return LocalMedia.NewsItem(
        malId = malId,
        url = url,
        title = title,
        date = date,
        authorUserName = authorUserName,
        authorUrl = authorUrl,
        forumUrl = forumUrl,
        images = images?.toLocal(),
        comments = comments,
        excerpt = excerpt
    )
}

fun Media.DiscussionComment.toLocal(): LocalMedia.DiscussionComment {
    return LocalMedia.DiscussionComment(
        url = url,
        authorUserName = authorUserName,
        authorUrl =authorUrl,
        date = date
    )
}

fun Media.Discussion.toLocal(): LocalMedia.Discussion {
    return LocalMedia.Discussion(
        malId = malId,
        url = url,
        title = title,
        date = date,
        authorUserName = authorUserName,
        authorUrl = authorUrl,
        comments = comments,
        lastComment = lastComment.toLocal()
    )
}

fun Media.StatisticsScore.toLocal(): LocalMedia.StatisticsScore {
    return LocalMedia.StatisticsScore(
        score = score,
        votes = votes,
        percentage = percentage
    )
}

fun Media.Statistics.toLocal(): LocalMedia.Statistics {
    return LocalMedia.Statistics(
        watching = watching,
        completed = completed,
        onHold = onHold,
        dropped = dropped,
        planToWatch = planToWatch,
        total = total,
        scores = scores.map { it.toLocal() }
    )
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
