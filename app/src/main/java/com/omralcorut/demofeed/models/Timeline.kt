package com.omralcorut.demofeed.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

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

@Parcelize
@Keep
data class Mention(
    val id: String,
    val profileImage: String,
    val fullname: String,
    val userName: String,
    val isFollowing: Boolean,
) : Parcelable