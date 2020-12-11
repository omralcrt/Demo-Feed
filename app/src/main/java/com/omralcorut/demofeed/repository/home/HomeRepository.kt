package com.omralcorut.demofeed.repository.home

import com.omralcorut.demofeed.models.Feature
import com.omralcorut.demofeed.models.Timeline

interface HomeRepository {
    suspend fun getFeatures(): Feature?
    suspend fun getTimeline(): Timeline?
}