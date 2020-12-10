package com.omralcorut.demofeed.models

data class Timeline(
    val timeline: List<TimelineDetail>
)

data class TimelineDetail(
    val id: String,
    val imageUrl: String,
    val mentions: List<Mention>,
    val date: String,
    val title: String,
    val countryCount: Int
)

data class Mention(
    val id: String,
    val profileImage: String,
    val fullname: String,
    val userName: String,
    val isFollowing: Boolean,
)