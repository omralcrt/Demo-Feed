package com.omralcorut.demofeed.models

data class Feature(
    val featured: List<FeatureDetail>
)

data class FeatureDetail(
    val id: String,
    val city: String,
    val country: String,
    val imageUrl: String
)