package com.omralcorut.demofeed.di.module

import com.omralcorut.demofeed.data.remote.Api
import com.omralcorut.demofeed.repository.home.HomeRepository
import com.omralcorut.demofeed.repository.home.HomeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideHomeRepository(
        api: Api
    ): HomeRepository {
        return HomeRepositoryImpl(api)
    }
}