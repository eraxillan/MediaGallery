package eraksillan.name.mediagallery.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eraksillan.name.mediagallery.BuildConfig
import eraksillan.name.mediagallery.remote.RateLimitInterceptor
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OkHttpModule {

    @Provides
    @Singleton
    fun okHttpCallFactory(): Call.Factory {
        return OkHttpClient.Builder()
            .addInterceptor(RateLimitInterceptor())
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        if (BuildConfig.DEBUG) {
                            setLevel(HttpLoggingInterceptor.Level.BODY)
                        }
                    },
            )
            .build()
    }
}
