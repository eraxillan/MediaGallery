package eraksillan.name.mediagallery.remote

interface RetrofitResponse<out T: Any> {
    fun toLocal(): T
}
