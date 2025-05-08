package eraksillan.name.mediagallery.mediastaff

sealed class MediaStaffAction {
    data object NavigateBackClicked : MediaStaffAction()
    class PersonClicked(val id: Int) : MediaStaffAction()
}
