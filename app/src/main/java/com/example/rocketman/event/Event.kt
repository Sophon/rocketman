package com.example.rocketman.event

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("details")
    val details: String = "",
    @SerializedName("event_date_unix")
    val eventDateUnix: Int = 0,
    @SerializedName("event_date_utc")
    val eventDateUtc: String = "",
    @SerializedName("links")
    val links: Links = Links(),
    @SerializedName("title")
    val title: String = ""
)

data class Links(
    @SerializedName("article")
    val article: String = ""
)