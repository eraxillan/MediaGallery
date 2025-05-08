package eraksillan.name.mediagallery.remote

import com.google.common.util.concurrent.RateLimiter
import okhttp3.Interceptor
import okhttp3.Response

class RateLimitInterceptor : Interceptor {
    // Rate is "1 permits per second", or "60 permits per minute"
    // Source: https://docs.api.jikan.moe/#section/Information/Rate-Limiting
    private val limiter = RateLimiter.create(1.0)

    override fun intercept(chain: Interceptor.Chain): Response {
        limiter.acquire(1)
        return chain.proceed(chain.request())
    }
}
