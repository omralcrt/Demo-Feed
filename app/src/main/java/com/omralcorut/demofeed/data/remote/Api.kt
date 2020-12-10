package com.omralcorut.demofeed.data.remote

import com.omralcorut.demofeed.models.Feature
import com.omralcorut.demofeed.models.Timeline
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("/featured")
    suspend fun getFeatured(): Response<Feature>

    @GET("/timeline")
    suspend fun getTimeline(
        @Query("page") page: Int
    ): Response<Timeline>
}