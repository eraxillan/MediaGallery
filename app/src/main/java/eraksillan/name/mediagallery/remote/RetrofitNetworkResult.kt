package eraksillan.name.mediagallery.remote

// https://proandroiddev.com/modeling-retrofit-responses-with-sealed-classes-and-coroutines-9d6302077dfe
sealed class RetrofitNetworkResult<I : RetrofitResponse<O>, O: Any> {
    /**
     * Represents a network result that successfully received a response containing body data
     */
    class Success<I : RetrofitResponse<O>, O: Any>(val data: I) : RetrofitNetworkResult<I, O>()

    /**
     * Represents a network result that successfully received a response containing an error message
     */
    class Error<I : RetrofitResponse<O>, O: Any>(val code: Int, val message: String?) : RetrofitNetworkResult<I, O>()

    /**
     * Represents a network result that faced an unexpected exception before getting a response
     * from the network such as IOException and UnKnownHostException
     */
    class Exception<I : RetrofitResponse<O>, O: Any>(val e: Throwable) : RetrofitNetworkResult<I, O>()
}
