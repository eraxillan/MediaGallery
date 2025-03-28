package eraksillan.name.mediagallery.seasonlist

import eraksillan.name.mediagallery.local.model.LocalMedia

sealed class SeasonListAction {
    class NavigateToSeason(val year: Int, val season: LocalMedia.Season) : SeasonListAction()
}
