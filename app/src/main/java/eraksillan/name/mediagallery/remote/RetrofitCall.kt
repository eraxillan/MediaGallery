package eraksillan.name.mediagallery.remote

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// https://proandroiddev.com/modeling-retrofit-responses-with-sealed-classes-and-coroutines-9d6302077dfe
class RetrofitCall<I : RetrofitResponse<O>, O : Any>(
    private val proxy: Call<I>
) : Call<RetrofitNetworkResult<I, O>> {

    override fun enqueue(callback: Callback<RetrofitNetworkResult<I, O>>) {
        proxy.enqueue(
            object : Callback<I> {
                override fun onResponse(call: Call<I>, response: Response<I>) {
                    val networkResult = handleApi { response }
                    callback.onResponse(this@RetrofitCall, Response.success(networkResult))
                }

                override fun onFailure(call: Call<I>, t: Throwable) {
                    val networkResult = RetrofitNetworkResult.Exception<I, O>(t)
                    callback.onResponse(this@RetrofitCall, Response.success(networkResult))
                }
            }
        )
    }

    override fun execute(): Response<RetrofitNetworkResult<I, O>> = throw NotImplementedError()
    override fun clone(): Call<RetrofitNetworkResult<I, O>> = RetrofitCall(proxy.clone())
    override fun request(): Request = proxy.request()
    override fun timeout(): Timeout = proxy.timeout()
    override fun isExecuted(): Boolean = proxy.isExecuted
    override fun isCanceled(): Boolean = proxy.isCanceled
    override fun cancel(): Unit = proxy.cancel()
}
