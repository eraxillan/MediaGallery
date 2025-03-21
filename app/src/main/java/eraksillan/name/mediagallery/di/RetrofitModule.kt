package eraksillan.name.mediagallery.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eraksillan.name.mediagallery.remote.RetrofitMediaRepository
import eraksillan.name.mediagallery.remote.RetrofitNetwork

@Module
@InstallIn(SingletonComponent::class)
interface RetrofitModule {

    @Binds
    fun binds(impl: RetrofitNetwork): RetrofitMediaRepository
}
