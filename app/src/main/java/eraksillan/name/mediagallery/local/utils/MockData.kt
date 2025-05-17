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

val mockStaff = listOf<LocalMedia.Person>(
    LocalMedia.Person(
        LocalMedia.PersonData(
            malId = 81348,
            url = "https://myanimelist.net/people/81348/Jinkeun_Choi",
            images = LocalMedia.Images(
                LocalMedia.ImageUrls(base = "https://cdn.myanimelist.net/images/questionmark_23.gif?s=f7dcbc4a4603d18356d3dfef8abd655c")
            ),
            name = "Choi, Jinkeun"
        ),
        positions = listOf("Producer")
    ),
    LocalMedia.Person(
        LocalMedia.PersonData(
            malId = 65510,
            url = "https://myanimelist.net/people/65510/Souta_Furuhashi",
            images = LocalMedia.Images(
                LocalMedia.ImageUrls(base = "https://cdn.myanimelist.net/images/voiceactors/3/75112.jpg?s=b8cf3f449adab7e7c39538d20927cfb4")
            ),
            name = "Furuhashi, Souta"
        ),
        positions = listOf("Producer")
    ),
    LocalMedia.Person(
        LocalMedia.PersonData(
            malId = 70967,
            url = "https://myanimelist.net/people/70967/Atsushi_Kaneko",
            images = LocalMedia.Images(
                LocalMedia.ImageUrls(base = "https://cdn.myanimelist.net/images/voiceactors/3/81133.jpg?s=912005fd82f8f122d02c0cb27ffeb09b")
            ),
            name = "Kaneko, Atsushi"
        ),
        positions = listOf("Producer")
    ),
    LocalMedia.Person(
        LocalMedia.PersonData(
            malId = 81349,
            url = "https://myanimelist.net/people/81349/Ayoung_Kim",
            images = LocalMedia.Images(
                LocalMedia.ImageUrls(base = "https://cdn.myanimelist.net/images/questionmark_23.gif?s=f7dcbc4a4603d18356d3dfef8abd655c")
            ),
            name = "Kim, Ayoung"
        ),
        positions = listOf("Producer")
    ),
    LocalMedia.Person(
        LocalMedia.PersonData(
            malId = 68136,
            url = "https://myanimelist.net/people/68136/Jinhae_Park",
            images = LocalMedia.Images(
                LocalMedia.ImageUrls(base = "https://cdn.myanimelist.net/images/questionmark_23.gif?s=f7dcbc4a4603d18356d3dfef8abd655c")
            ),
            name = "Park, Jinhae"
        ),
        positions = listOf("Producer")
    ),
)

val mockThemes = LocalMedia.Themes(
    openings = listOf("\"ReawakeR (feat. Felix of Stray Kids)\" by LiSA (eps 1-11,13)"),
    endings = listOf(
        "1: \"UN-APEX\" by TK from Ling tosite sigure (TK from 凛として時雨) (eps 1-12)",
        "2: \"LEveL\" by SawanoHiroyuki[nZk]:TOMORROW X TOGETHER (eps 13)"
    )
)

val mockReviews = listOf<LocalMedia.Review>(
    LocalMedia.Review(
        user = LocalMedia.ReviewUser(
            name = "Marinate1016",
            url = "https://myanimelist.net/profile/Marinate1016",
            images = LocalMedia.Images(
                jpeg = LocalMedia.ImageUrls(base = "https://cdn.myanimelist.net/s/common/userimages/a6ec3ffc-a661-4605-8619-ce0bf635272a_42x62_i?s=31c20b71d4d91a8ce0085e77657afad2"),
                webp = LocalMedia.ImageUrls(base = "https://cdn.myanimelist.net/s/common/userimages/a6ec3ffc-a661-4605-8619-ce0bf635272a_42x62_i?s=31c20b71d4d91a8ce0085e77657afad2")
            )
        ),
        malId = 556582,
        url = "https://myanimelist.net/reviews.php?id=556582",
        type = "anime",
        reactions = LocalMedia.ReviewReactions(
            overall = 241,
            nice = 126,
            loveIt = 36,
            funny = 39,
            confusing = 21,
            informative = 0,
            wellWritten = 19,
            creative = 0
        ),
        date = "2025-03-29T08:52:00+00:00",
        review = "No amount of memes or social media posts could ever make me hate Solo Leveling. No, Solo Leveling doesn’t have the strongest story, yes Jinwoo aura farms every episode and beats the bad guy, yes A-1 and Sawano help take this series to new heights, but is it a crime to just enjoy shows for the spectacle? At the end of the day, anime is about entertainment and pushing the envelope of the animation medium, which solo leveling does. Not everything needs to reinvent the wheel, it’s sort of like expecting every meal you eat to be a Michelin star restaurant. It’s ok to enjoya fast food meal every now and again if you just view and accept it as such. That’s basically how I feel about Solo Leveling. It’s not the best anime ever, but it is very enjoyable and unlike many people, I actually enjoy the story, characters and premise. Season 2 was only an upgrade in every way for me and is a must watch for any action or sakuga fan.\n\nWhile I don’t think Solo Leveling’s story is the best, I have to say I’m in the minority of people who actually think it’s kind of good? Sure, it’s slow to play out, but there’s been a clear goal in this series from day 1, curing Jinwoo’s mother and not so subtle hints about the magic beast gates/raids being more than they seem. Season 2 builds on this by clearly setting up some greater power that’s pitting humanity against the magic beasts. For what reason? We don’t know, it’s a slow build up, but I completely disagree with the logic that “Solo leveling has no story” I really enjoy the mystery and speculating every week about what’s going on in the world. If you’re looking for something extremely complex then yea, Solo leveling isn’t going to knock your socks off, it’s unashamedly a power fantasy, but if you go in with an open mind and actually analyze it in good faith I think many people will agree that there’s a decent amount of intrigue here.\n\nMy one real complaint with Solo leveling is that Jinwoo feels so one dimensional. It’s like he actually regressed as a person, but improved as a hunter since he got his abilities. Sometimes it feels like watching a cardboard cutout rather than a person. That’s just me speaking as an anime only who’s only got these two seasons to go off of though. I assume he’s going to actually change as the story goes on and new threats emerge, but damn he just feels so edgy in this season. This isn’t entirely out of place given the stuff he’s been through in the past, his family situation, etc., but I would like to see a little more personality!\n\nGoes without saying A-1 and Sawano delivered yet another masterclass this season. I think the OST is even better, the OP with my goat Felix from stray kidz and LiSA has been on repeat nonstop since it dropped. The fights in this season surpass the first and the art is very good as usual. The production quality is amazing, it’s one of the best looking anime out there and it just reaffirms my belief that A-1 pictures at their best are as good as anyone in this industry. SAO Alicization, 86 and now Solo Leveling have shown that they have arrived and are here to stay. So many times during this season I just found my jaw on the floor with how great the choreography and storyboarding looked. Bravo.\n\nIf you step back and simply consider Solo Leveling as an easily digestible feast on the eyes with a simple, but interesting plot, you’ll enjoy this. If you come into it trying to be an anime critic and nitpick every aspect of the series, you’ll be miserable. There’s a reason why it’s one of the most popular manhwa ever. \n\nSolo leveling season 2 gets 9 out of 10",
        score = 9,
        tags = listOf("Recommended"),
        isSpoiler = false,
        isPreliminary = false,
        episodesWatched = null
    ),
    LocalMedia.Review(
        user = LocalMedia.ReviewUser(
            name = "Bardwyne",
            url = "https://myanimelist.net/profile/Bardwyne",
            images = LocalMedia.Images(
                jpeg = LocalMedia.ImageUrls(base = "https://cdn.myanimelist.net/s/common/userimages/e22e3484-9970-4fc7-b548-2f38e79c3359_42x62_i?s=98fc9ce58519074167eae46cdc8625ff"),
                webp = LocalMedia.ImageUrls(base = "https://cdn.myanimelist.net/s/common/userimages/e22e3484-9970-4fc7-b548-2f38e79c3359_42x62_i?s=98fc9ce58519074167eae46cdc8625ff")
            )
        ),
        malId = 556652,
        url = "https://myanimelist.net/reviews.php?id=556652",
        type = "anime",
        reactions = LocalMedia.ReviewReactions(
            overall = 123,
            nice = 81,
            loveIt = 1,
            funny = 15,
            confusing = 21,
            informative = 2,
            wellWritten = 2,
            creative = 1
        ),
        date = "2025-03-29T14:45:00+00:00",
        review = "Just as the last season, Solo Leveling s2 has flashy action and animation, but ultimately it's pretty substanceless - relying on hype and \"aura\" rather than characters or narrative. Considering that, It also takes itself way too seriously. Even for those who specifically want action they may find themselves uninterested in the fights due to a lack of compelling motivations or interest in the combatants. And even if that isn't an issue - plenty of shows could scratch the same itch for surface level action and hype, some of which are more technically impressive or otherwise better. But if all you need is some cool fights andhype - this is your jam.\nIf not, you're better off looking elsewhere.",
        score = 6,
        tags = listOf("Mixed Feelings"),
        isSpoiler = false,
        isPreliminary = false,
        episodesWatched = null
    ),
    LocalMedia.Review(
        user = LocalMedia.ReviewUser(
            name = "Tkit",
            url = "https://myanimelist.net/profile/Tkit",
            images = LocalMedia.Images(
                jpeg = LocalMedia.ImageUrls(base = "https://cdn.myanimelist.net/s/common/userimages/56bd3ea6-2bf8-47ed-8f0e-c6275dfbce17_225w?s=5ace8145f949260580e4840da945d578"),
                webp = LocalMedia.ImageUrls(base = "https://cdn.myanimelist.net/s/common/userimages/56bd3ea6-2bf8-47ed-8f0e-c6275dfbce17_225w?s=5ace8145f949260580e4840da945d578")
            )
        ),
        malId = 556578,
        url = "https://myanimelist.net/reviews.php?id=556578",
        type = "anime",
        reactions = LocalMedia.ReviewReactions(
            overall = 523,
            nice = 234,
            loveIt = 22,
            funny = 135,
            confusing = 121,
            informative = 0,
            wellWritten = 10,
            creative = 1
        ),
        date = "2025-04-02T08:39:00+00:00",
        review = "This is genuinely the most pathetic new mainstream anime I've seen. Solo Leveling shows no ambition, no effort and no emotion. Even if considered among the big range of slop power fantasy it is one of the blandest of guilty pleasures one can have. There is no skill in terms of writing here, therefore it is critical to have a thorough look at Solo Leveling's animation and how it handles its role as said power fantasy. Those are its only values and even here it is overhyped and lacking. Everything here is made to revolve around our great Sung lil-bitch. Structure of the story and the'characters' are here to highlight how cool the mc is and to react to how cool he is, that is their whole goal. Every female is made to start lusting over Sung except for his family, which is also here to show that he is cool and cares about his family, beside that they react (as the rest of npcs do) to his epic aura. Story in here follows roughly this structure:\nSung 'needs to get stronger' (we will get back to it) \nHe goes to grind, which goes effortlessly \nHe encounters a boss which is strong enough to warrant a longer fight, but still never had a chance to beat him.\nThroughout or at the end of those steps there are many npcs to react to his awesomeness\nRepeat 7 times \nFinish it with a super boss which trashes npcs, but ends up destroyed like the rest\n\nThe worst part about this awful story is how easily it could have been improved. Why Sung lil-bitch needs to get stronger? To get money for his sister and find a way to save his mother. Why isn't there a time limit to this? His mother can just lay in bed for the next 10 years and his sister was doing fine even without the money. All they had to do was having his mother get so ill that she would die in two months or something and have it destroy her body. Same with the sister, just have her in heavy debt and maybe forced to work some side job that is dangerous and hinders her school life. Those are the smallest, easiest to implement changes and already you have some urgency, drama and tension which don't exist in this show as of now. Sung himself could have been also easily improved. Show hints at his moral fall but does nothing with it. Dude casually murders someone because in the state of panic a frightened npc attacks him. There are moments when he lacks empathy and comments on it, why isn't it expanded into something meaningful? Instead he is just cool, edgy and always wins. That is what I mean by the lack of ambition, the power fantasy aspect is just MC STRONG with no creativity or ability.\n\nThe fact that this show doesn't seem to realize how much it sucks is very frustrating. Anime genuinely tries to have me worried about the moron npcs like they are people or something. Oh no, will the sniffa lady designed to be in love with lil-bitch die on the first real mission I see her on? Oh no will those heroic fighters get wiped out (repeat 6 times)? Same with the mc. The funniest shit is how they try to build tension for every unlosable fight. I loved for example the epic music for the 1st episode fight between a summon and random bear, truly an epic face off. Even the premise of the show is abandoned. I know it is called Solo Leveling, but instead of just having grind from games they could have brough some fun gaming strategies. Never have I seen Sung go 'this fight looks tough let me buff myself, setup some traps and creativly use some skills/equipment' its always just SMASH or SLASH.\n\nEnough about obvious flaws, let's talk visuals.\nSolo Leveling is widely considered pretty which is true, it is also important to note that second season has a much better production quality than the first one. On the other hand one hears so much about said quality that you might expect something revolutionary like a consistent peak animation, I'm here to say that that is not the case. Before we focus on the fights it is important to acknowledge that we don't always have said fights and outside of them anime decreases in quality. Its not awful, it is just standard, sometimes lighting will be pretty cool tho. In the fights by far VFX are the best, some elemental spells look beautiful. Lightning, particles, etc. are cool and movement made slick. Fights have quite a few animation highlights to the point that sometimes we even get some decent choreography. It happens quite rarely (most prominent in the 6th episode). It is quite a high accomplishments for a slop anime, but it is important to remember than it has been done better before. Most disappointing were the backgrounds which at least sometimes existed in fights, but beside that weren't doing anything too impressive. You can see what I mean in the 12th episode. We change the fight to those controversial floating lines which always when executed correctly exists to highlight good background animation, but here said background is either non existent or gray and boring, with not much of anything going on.\nTalking about the climactic fight it was the biggest disappointment of Solo Leveling. 60% was just slapping each other, there was the aforementioned fumble with lines and the rest was also just vfx line attacks followed by damage, it looked cool, but was weaker than most of the other big fights of this series.\nWhole scale of the action sequences can't be handled by the anime. All the time we have big team fights and with so many individuals we often get to see a lot of poor cgi, which extends to cars as well.\n\nTo wrap it up, yes fights look good, but not good enough to carry this disaster on its own. That is why you can only enjoy this show if you can get entertainment from its super boring form of a classic power fantasy. You may say I'm nitpicking, but you have to realise that if all you have is animation than that is the level of scrutiny you are going to get. I genuinely believe that instead of watching this hit piece of shit you should invest 10 minutes of your life on research and you will easily find a truly good anime that has good animation and/or good power fantasy.",
        score = 3,
        tags = listOf("Not Recommended", "Funny"),
        isSpoiler = false,
        isPreliminary = false,
        episodesWatched = null
    )
)
