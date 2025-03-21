package eraksillan.name.mediagallery.remote

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

// https://proandroiddev.com/modeling-retrofit-responses-with-sealed-classes-and-coroutines-9d6302077dfe
class RetrofitCallAdapter(
    private val resultType: Type
) : CallAdapter<RetrofitResponse<Type>, Call<RetrofitNetworkResult<RetrofitResponse<Type>, Type>>> {

    override fun responseType(): Type = resultType

    override fun adapt(call: Call<RetrofitResponse<Type>>): Call<RetrofitNetworkResult<RetrofitResponse<Type>, Type>> {
        return RetrofitCall<RetrofitResponse<Type>, Type>(call)
    }
}
