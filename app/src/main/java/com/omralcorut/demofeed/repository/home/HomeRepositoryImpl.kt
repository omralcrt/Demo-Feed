package com.omralcorut.demofeed.repository.home

import com.omralcorut.demofeed.data.remote.Api
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
class HomeRepositoryImpl @Inject constructor(
    private val api: Api
) : HomeRepository {

    override suspend fun getFeatures() = api.getFeatured().body()

    override suspend fun getTimeline() = api.getTimeline(1).body()
}