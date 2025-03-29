package eraksillan.name.mediagallery.local.utils

import eraksillan.name.mediagallery.local.model.LocalMedia

val mockMedia = LocalMedia(
    malId = 4459,
    url = "https://myanimelist.net/anime/4459/Ojarumaru",
    images = LocalMedia.Images(
        jpeg = LocalMedia.ImageUrls(
            base = "https://cdn.myanimelist.net/images/anime/1839/132018.jpg",
            small = "https://cdn.myanimelist.net/images/anime/1839/132018t.jpg",
            large = "https://cdn.myanimelist.net/images/anime/1839/132018l.jpg"
        ),
        webp = LocalMedia.ImageUrls(
            base = "https://cdn.myanimelist.net/images/anime/1839/132018.webp",
            small = "https://cdn.myanimelist.net/images/anime/1839/132018t.webp",
            large = "https://cdn.myanimelist.net/images/anime/1839/132018l.webp"
        ),
    ),
    trailer = LocalMedia.Trailer(
        youtubeId = null,
        url = null,
        embedUrl = null,
        images = LocalMedia.ImageUrls()
    ),
    approved = true,
    titles = listOf<LocalMedia.Title>(
        LocalMedia.Title(type = LocalMedia.Title.Type.DEFAULT, title = "Ojarumaru"),
        LocalMedia.Title(type = LocalMedia.Title.Type.JAPANESE, title = "おじゃる丸"),
        LocalMedia.Title(type = LocalMedia.Title.Type.ENGLISH, title = "Prince Mackaroo"),
    ),
    type = LocalMedia.Type.TV,
    source = "Original",
    episodes = null,
    status = LocalMedia.Status.CURRENTLY_AIRING,
    airing = true,
    aired = LocalMedia.Aired(
        from = "1998-10-05T00:00:00+00:00",
        to = null,
        prop = LocalMedia.Aired.Prop(
            from = LocalMedia.Aired.Date(day = 5, month = 10, year = 1998),
            to = LocalMedia.Aired.Date(day = null, month = null, year = null)
        ),
        displayString = "Oct 5, 1998 to ?"
    ),
    duration = "10 min",
    rating = LocalMedia.Rating.G,
    score = 6.38f,
    scoredBy = 554,
    rank = 7785,
    popularity = 11543,
    members = 2223,
    favorites = 5,
    synopsis = "In the Heian era, around 1000 years ago, a young boy of noble family named Ojarumaru...",
    background = "The second season that aired in 1999 (episodes 91-180) was awarded the Excellence...",
    season = LocalMedia.Season.FALL,
    year = 1998,
    broadcast = LocalMedia.Broadcast(
        day = null,
        time = null,
        timeZone = null,
        displayString = "Not scheduled once per week"
    ),
    producers = listOf<LocalMedia.Entity>(
        LocalMedia.Entity(
            malId = 111,
            type = "anime",
            name = "NHK",
            url = "https://myanimelist.net/anime/producer/111/NHK"
        )
    ),
    licensors = listOf<LocalMedia.Entity>(
        LocalMedia.Entity(
            malId = 311,
            type = "anime",
            name = "Enoki Films",
            url = "https://myanimelist.net/anime/producer/311/Enoki_Films"
        )
    ),
    studios = listOf<LocalMedia.Entity>(
        LocalMedia.Entity(
            malId = 36,
            type = "anime",
            name = "Gallop",
            url = "https://myanimelist.net/anime/producer/36/Gallop"
        )
    ),
    genres = listOf<LocalMedia.Entity>(
        LocalMedia.Entity(
            malId = 2,
            type = "anime",
            name = "Adventure",
            url = "https://myanimelist.net/anime/genre/2/Adventure"
        ),
        LocalMedia.Entity(
            malId = 46,
            type = "anime",
            name = "Award Winning",
            url = "https://myanimelist.net/anime/genre/46/Award_Winning"
        ),
        LocalMedia.Entity(
            malId = 4,
            type = "anime",
            name = "Comedy",
            url = "https://myanimelist.net/anime/genre/4/Comedy"
        ),
        LocalMedia.Entity(
            malId = 10,
            type = "anime",
            name = "Fantasy",
            url = "https://myanimelist.net/anime/genre/10/Fantasy"
        )
    ),
    explicitGenres = listOf<LocalMedia.Entity>(),
    themes = listOf<LocalMedia.Entity>(),
    demographics = listOf<LocalMedia.Entity>(
        LocalMedia.Entity(
            malId = 15,
            type = "anime",
            name = "Kids",
            url = "https://myanimelist.net/anime/genre/15/Kids"
        )
    ),
)
