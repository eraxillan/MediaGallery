package eraksillan.name.mediagallery.remote

import retrofit2.HttpException
import retrofit2.Response

fun <I : RetrofitResponse<O>, O : Any> handleApi(
    execute: () -> Response<I>
): RetrofitNetworkResult<I, O> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            RetrofitNetworkResult.Success(body)
        } else {
            RetrofitNetworkResult.Error(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        RetrofitNetworkResult.Error(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        RetrofitNetworkResult.Exception(e)
    }
}
