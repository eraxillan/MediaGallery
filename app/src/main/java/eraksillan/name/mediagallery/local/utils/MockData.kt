package eraksillan.name.mediagallery.local.utils

import eraksillan.name.mediagallery.local.model.LocalMedia

val mockMedia = LocalMedia(
    malId = 58567,
    url = "https://myanimelist.net/anime/58567/Ore_dake_Level_Up_na_Ken_Season_2__Arise_from_the_Shadow",
    images = LocalMedia.Images(
        jpeg = LocalMedia.ImageUrls(
            base = "https://cdn.myanimelist.net/images/anime/1448/147351.jpg",
            small = "https://cdn.myanimelist.net/images/anime/1448/147351t.jpg",
            large = "https://cdn.myanimelist.net/images/anime/1448/147351l.jpg"
        ),
        webp = LocalMedia.ImageUrls(
            base = "https://cdn.myanimelist.net/images/anime/1448/147351.webp",
            small = "https://cdn.myanimelist.net/images/anime/1448/147351t.webp",
            large = "https://cdn.myanimelist.net/images/anime/1448/147351l.webp"
        ),
    ),
    trailer = LocalMedia.Trailer(
        youtubeId = "GDMXGzjJzS4",
        url = "https://www.youtube.com/watch?v=GDMXGzjJzS4",
        embedUrl = "https://www.youtube.com/embed/GDMXGzjJzS4?enablejsapi=1&wmode=opaque&autoplay=1",
        images = LocalMedia.ImageUrls(
            base = "https://img.youtube.com/vi/GDMXGzjJzS4/default.jpg",
            small = "https://img.youtube.com/vi/GDMXGzjJzS4/sddefault.jpg",
            medium = "https://img.youtube.com/vi/GDMXGzjJzS4/mqdefault.jpg",
            large = "https://img.youtube.com/vi/GDMXGzjJzS4/hqdefault.jpg",
            extraLarge = "https://img.youtube.com/vi/GDMXGzjJzS4/maxresdefault.jpg"
        )
    ),
    approved = true,
    titles = listOf<LocalMedia.Title>(
        LocalMedia.Title(type = LocalMedia.Title.Type.DEFAULT, title = "Ore dake Level Up na Ken Season 2: Arise from the Shadow"),
        LocalMedia.Title(type = LocalMedia.Title.Type.SYNONYM, title = "Solo Leveling Second Season"),
        LocalMedia.Title(type = LocalMedia.Title.Type.JAPANESE, title = "俺だけレベルアップな件 Season 2 -Arise from the Shadow-"),
        LocalMedia.Title(type = LocalMedia.Title.Type.ENGLISH, title = "Solo Leveling Season 2: Arise from the Shadow"),
    ),
    type = LocalMedia.Type.TV,
    source = "Web manga",
    episodes = 13,
    status = LocalMedia.Status.FINISHED_AIRING,
    airing = false,
    aired = LocalMedia.Aired(
        from = "2025-01-05T00:00:00+00:00",
        to = "2025-03-30T00:00:00+00:00",
        prop = LocalMedia.Aired.Prop(
            from = LocalMedia.Aired.Date(day = 5, month = 1, year = 2025),
            to = LocalMedia.Aired.Date(day = 30, month = 3, year = 2025)
        ),
        displayString = "Jan 5, 2025 to Mar 30, 2025"
    ),
    duration = "23 min per ep",
    rating = LocalMedia.Rating.R_17,
    score = 8.81f,
    scoredBy = 258400,
    rank = 32,
    popularity = 473,
    members = 507978,
    favorites = 7887,
    synopsis = "Sung Jin-Woo, dubbed the weakest hunter of all mankind, grows stronger by the day with the supernatural powers he has gained. However, keeping his skills hidden becomes more difficult as dungeon-related incidents pile up around him.\\n\\nWhen Jin-Woo and a few other low-ranked hunters are the only survivors of a dungeon that turns out to be a bigger challenge than initially expected, he draws attention once again, and hunter guilds take an increased interest in him. Meanwhile, a strange hunter who has been lost for ten years returns with a dire warning about an upcoming catastrophic event. As the calamity looms closer, Jin-Woo must continue leveling up to make sure nothing stops him from reaching his ultimate goal—saving the life of his mother.\\n\\n[Written by MAL Rewrite]",
    background = "",
    season = LocalMedia.Season.WINTER,
    year = 2025,
    broadcast = LocalMedia.Broadcast(
        day = "Sundays",
        time = "00:00",
        timeZone = "Asia/Tokyo",
        displayString = "Sundays at 00:00 (JST)"
    ),
    producers = listOf<LocalMedia.Entity>(
        LocalMedia.Entity(
            malId = 17,
            type = "anime",
            name = "Aniplex",
            url = "https://myanimelist.net/anime/producer/17/Aniplex"
        ),
        LocalMedia.Entity(
            malId = 1468,
            type = "anime",
            name = "Crunchyroll",
            url = "https://myanimelist.net/anime/producer/1468/Crunchyroll"
        ),
        LocalMedia.Entity(
            malId = 1858,
            type = "anime",
            name = "Sonilude",
            url = "https://myanimelist.net/anime/producer/1858/Sonilude"
        ),
        LocalMedia.Entity(
            malId = 2837,
            type = "anime",
            name = "Netmarble",
            url = "https://myanimelist.net/anime/producer/2837/Netmarble"
        ),
        LocalMedia.Entity(
            malId = 2839,
            type = "anime",
            name = "Kakao piccoma",
            url = "https://myanimelist.net/anime/producer/2839/Kakao_piccoma"
        ),
        LocalMedia.Entity(
            malId = 2872,
            type = "anime",
            name = "D&C Media",
            url = "https://myanimelist.net/anime/producer/2872/D_C_Media"
        )
    ),
    licensors = emptyList(),
    studios = listOf<LocalMedia.Entity>(
        LocalMedia.Entity(
            malId = 56,
            type = "anime",
            name = "A-1 Pictures",
            url = "https://myanimelist.net/anime/producer/56/A-1_Pictures"
        )
    ),
    genres = listOf<LocalMedia.Entity>(
        LocalMedia.Entity(
            malId = 1,
            type = "anime",
            name = "Action",
            url = "https://myanimelist.net/anime/genre/1/Action"
        ),
        LocalMedia.Entity(
            malId = 2,
            type = "anime",
            name = "Adventure",
            url = "https://myanimelist.net/anime/genre/2/Adventure"
        ),
        LocalMedia.Entity(
            malId = 10,
            type = "anime",
            name = "Fantasy",
            url = "https://myanimelist.net/anime/genre/10/Fantasy"
        )
    ),
    explicitGenres = emptyList(),
    themes = listOf<LocalMedia.Entity>(
        LocalMedia.Entity(
            malId = 50,
            type = "anime",
            name = "Adult Cast",
            url = "https://myanimelist.net/anime/genre/50/Adult_Cast"
        ),
        LocalMedia.Entity(
            malId = 82,
            type = "anime",
            name = "Urban Fantasy",
            url = "https://myanimelist.net/anime/genre/82/Urban_Fantasy"
        )
    ),
    demographics = emptyList(),
)

val mockExternalLinks = listOf(
    LocalMedia.Link("Official Site", "https://sololeveling-anime.net"),
    LocalMedia.Link("@sololeveling_en", "https://twitter.com/sololeveling_en"),
    LocalMedia.Link("@sololeveling_pr", "https://twitter.com/sololeveling_pr"),
    LocalMedia.Link("AniDB", "https://anidb.net/perl-bin/animedb.pl?show=anime&aid=18576"),
    LocalMedia.Link("ANN", "https://www.animenewsnetwork.com/encyclopedia/anime.php?id=32617"),
    LocalMedia.Link("Wikipedia", "https://en.wikipedia.org/wiki/Solo_Leveling"),
    LocalMedia.Link("Wikipedia", "https://ja.wikipedia.org/wiki/俺だけレベルアップな件"),
    LocalMedia.Link("Syoboi", "https://cal.syoboi.jp/tid/7335")
)

val mockRelations = listOf(
    LocalMedia.Relation(
        LocalMedia.Relation.Type.PREQUEL,
        listOf(
            LocalMedia.Entity(
                52299,
                "anime",
                "Ore dake Level Up na Ken",
                "https://myanimelist.net/anime/52299/Ore_dake_Level_Up_na_Ken"
            )
        )
    ),
    LocalMedia.Relation(
        LocalMedia.Relation.Type.ADAPTATION,
        listOf(
            LocalMedia.Entity(
                121496,
                "manga",
                "Solo Leveling",
                "https://myanimelist.net/manga/121496/Solo_Leveling"
            )
        )
    )
)

val mockMoreInfo = "Episodes 1-2 were previewed in the theatrical screening of Ore dake Level Up na Ken: ReAwakening on November 28, 2024. Regular broadcasting began on January 5, 2025."

val mockCast = listOf<LocalMedia.Cast>(
    LocalMedia.Cast(
        character = LocalMedia.Cast.MediaCharacter(
            malId = 174185,
            url = "https://myanimelist.net/character/174185/Jin-Woo_Sung",
            images = LocalMedia.Images(
                jpeg = LocalMedia.ImageUrls(
                    base = "https://cdn.myanimelist.net/images/characters/2/540692.jpg?s=84ef54c5990bd67985d24060a9ff3e84"
                ),
                webp = LocalMedia.ImageUrls(
                    base = "https://cdn.myanimelist.net/images/characters/2/540692.webp?s=84ef54c5990bd67985d24060a9ff3e84",
                    small = "https://cdn.myanimelist.net/images/characters/2/540692t.webp?s=84ef54c5990bd67985d24060a9ff3e84"
                )
            ),
            name = "Sung, Jin-Woo"
        ),
        role = "Main",
        favorites = 18807,
        voiceActors = listOf(
            LocalMedia.Cast.MediaVoiceActor(
                person = LocalMedia.Cast.MediaVoiceActor.Person(
                    malId = 47739,
                    url = "https://myanimelist.net/people/47739/Taito_Ban",
                    images = LocalMedia.Images(
                        jpeg = LocalMedia.ImageUrls(
                            base = "https://cdn.myanimelist.net/images/voiceactors/3/74502.jpg?s=8425b98a40dcca2bd35815c9356ec97c"
                        )
                    ),
                    name = "Ban, Taito",
                ),
                language = "Japanese"
            ),
            LocalMedia.Cast.MediaVoiceActor(
                person = LocalMedia.Cast.MediaVoiceActor.Person(
                    malId = 12861,
                    url = "https://myanimelist.net/people/12861/Charles_Emmanuel",
                    images = LocalMedia.Images(
                        jpeg = LocalMedia.ImageUrls(
                            base = "https://cdn.myanimelist.net/images/voiceactors/3/60298.jpg?s=e631e54968a9b8e5a8bc88d43233f89d"
                        )
                    ),
                    name = "Emmanuel, Charles",
                ),
                language = "Portuguese (BR)"
            ),
            LocalMedia.Cast.MediaVoiceActor(
                person = LocalMedia.Cast.MediaVoiceActor.Person(
                    malId = 50080,
                    url = "https://myanimelist.net/people/50080/Aleks_Le",
                    images = LocalMedia.Images(
                        jpeg = LocalMedia.ImageUrls(
                            base = "https://cdn.myanimelist.net/images/voiceactors/1/84619.jpg?s=6e382ce44f60c5c5e724d127dac1c79d"
                        )
                    ),
                    name = "Le, Aleks",
                ),
                language = "English"
            ),
            LocalMedia.Cast.MediaVoiceActor(
                person = LocalMedia.Cast.MediaVoiceActor.Person(
                    malId = 55022,
                    url = "https://myanimelist.net/people/55022/Alexis_Flamant",
                    images = LocalMedia.Images(
                        jpeg = LocalMedia.ImageUrls(
                            base = "https://cdn.myanimelist.net/images/voiceactors/1/68228.jpg?s=80ff317b5b0fb7983663a55f18291158"
                        )
                    ),
                    name = "Flamant, Alexis",
                ),
                language = "French"
            ),
            LocalMedia.Cast.MediaVoiceActor(
                person = LocalMedia.Cast.MediaVoiceActor.Person(
                    malId = 42614,
                    url = "https://myanimelist.net/people/42614/Federico_Campaiola",
                    images = LocalMedia.Images(
                        jpeg = LocalMedia.ImageUrls(
                            base = "https://cdn.myanimelist.net/images/voiceactors/1/45968.jpg?s=75feebb1cffc876a25fa1c09e5cb2da1"
                        )
                    ),
                    name = "Campaiola, Federico",
                ),
                language = "Italian"
            )
        )
    ),
    LocalMedia.Cast(
        character = LocalMedia.Cast.MediaCharacter(
            malId = 173982,
            url = "https://myanimelist.net/character/173982/Sang-Min_Ahn",
            images = LocalMedia.Images(
                jpeg = LocalMedia.ImageUrls(
                    base = "https://cdn.myanimelist.net/images/characters/2/403236.jpg?s=90cdf39e9c21d0f7a4cfd94839ab3c0f"
                ),
                webp = LocalMedia.ImageUrls(
                    base = "https://cdn.myanimelist.net/images/characters/2/403236.webp?s=90cdf39e9c21d0f7a4cfd94839ab3c0f",
                    small = "https://cdn.myanimelist.net/images/characters/2/403236t.webp?s=90cdf39e9c21d0f7a4cfd94839ab3c0f"
                )
            ),
            name = "Ahn, Sang-Min"
        ),
        role = "Supporting",
        favorites = 0,
        voiceActors = listOf(
            LocalMedia.Cast.MediaVoiceActor(
                person = LocalMedia.Cast.MediaVoiceActor.Person(
                    malId = 11840,
                    url = "https://myanimelist.net/people/11840/Hironori_Kondou",
                    images = LocalMedia.Images(
                        jpeg = LocalMedia.ImageUrls(
                            base = "https://cdn.myanimelist.net/images/voiceactors/2/75762.jpg?s=ee5cc5c4a31a9d0112bd8eb977d61b47"
                        )
                    ),
                    name = "Kondou, Hironori",
                ),
                language = "Japanese"
            ),
            LocalMedia.Cast.MediaVoiceActor(
                person = LocalMedia.Cast.MediaVoiceActor.Person(
                    malId = 54502,
                    url = "https://myanimelist.net/people/54502/Cory_Yee",
                    images = LocalMedia.Images(
                        jpeg = LocalMedia.ImageUrls(
                            base = "https://cdn.myanimelist.net/images/voiceactors/1/70948.jpg?s=73c5f11d58107a9b782cbafa36226863"
                        )
                    ),
                    name = "Yee, Cory",
                ),
                language = "English"
            ),
            LocalMedia.Cast.MediaVoiceActor(
                person = LocalMedia.Cast.MediaVoiceActor.Person(
                    malId = 55946,
                    url = "https://myanimelist.net/people/55946/Ailton_Rosa",
                    images = LocalMedia.Images(
                        jpeg = LocalMedia.ImageUrls(
                            base = "https://cdn.myanimelist.net/images/voiceactors/2/79873.jpg?s=b979decb37542478f5bfff3062d3cae1"
                        )
                    ),
                    name = "Rosa, Ailton",
                ),
                language = "Portuguese (BR)"
            ),
            LocalMedia.Cast.MediaVoiceActor(
                person = LocalMedia.Cast.MediaVoiceActor.Person(
                    malId = 56796,
                    url = "https://myanimelist.net/people/56796/Marc_Weiss",
                    images = LocalMedia.Images(
                        jpeg = LocalMedia.ImageUrls(
                            base = "https://cdn.myanimelist.net/images/voiceactors/2/67726.jpg?s=03d41e4e7f63b95530b7ae41e455953d"
                        )
                    ),
                    name = "Weiss, Marc",
                ),
                language = "French"
            ),
        )
    )
)
