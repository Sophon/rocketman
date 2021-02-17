package com.example.rocketman.launch

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Entity
@Parcelize
data class Launch(
    @SerializedName("auto_update")
    val autoUpdate: Boolean = false,
    @SerializedName("capsules")
    val capsules: List<String> = listOf(),
    @SerializedName("cores")
    val cores: List<Core> = listOf(),
    @SerializedName("crew")
    val crew: List<@RawValue Any> = listOf(),
    @SerializedName("date_local")
    val dateLocal: String = "",
    @SerializedName("date_precision")
    val datePrecision: String = "",
    @SerializedName("date_unix")
    val dateUnix: Int = 0,
    @SerializedName("date_utc")
    val dateUtc: String = "",
    @SerializedName("details")
    val details: String = "",
    @SerializedName("failures")
    @Transient
    val failures: List<@RawValue Any> = listOf(),
    @SerializedName("fairings")
    val fairings: @RawValue Any? = null,
    @SerializedName("flight_number")
    val flightNumber: Int = 0,
    @SerializedName("id")
    @PrimaryKey
    val id: String = "",
    @SerializedName("launchpad")
    val launchpad: String = "",
    @SerializedName("links")
    val links: Links = Links(),
    @SerializedName("name")
    val name: String = "",
    @SerializedName("net")
    val net: Boolean = false,
    @SerializedName("payloads")
    val payloads: List<String> = listOf(),
    @SerializedName("rocket")
    val rocket: String = "",
    @SerializedName("ships")
    val ships: List<@RawValue Any> = listOf(),
    @SerializedName("static_fire_date_unix")
    val staticFireDateUnix: Int = 0,
    @SerializedName("static_fire_date_utc")
    val staticFireDateUtc: String = "",
    @SerializedName("success")
    val success: Boolean = false,
    @SerializedName("tdb")
    val tdb: Boolean = false,
    @SerializedName("upcoming")
    val upcoming: Boolean = false,
    @SerializedName("window")
    val window: Int = 0
): Parcelable

@Parcelize
data class Core(
    @SerializedName("core")
    val core: String = "",
    @SerializedName("flight")
    val flight: Int = 0,
    @SerializedName("gridfins")
    val gridfins: Boolean = false,
    @SerializedName("landing_attempt")
    val landingAttempt: Boolean = false,
    @SerializedName("landing_success")
    val landingSuccess: Boolean = false,
    @SerializedName("landing_type")
    val landingType: String = "",
    @SerializedName("landpad")
    val landpad: String = "",
    @SerializedName("legs")
    val legs: Boolean = false,
    @SerializedName("reused")
    val reused: Boolean = false
): Parcelable

@Parcelize
data class Links(
    @SerializedName("article")
    val article: String = "",
    @SerializedName("flickr")
    val flickr: Flickr = Flickr(),
    @SerializedName("patch")
    val patch: Patch = Patch(),
    @SerializedName("presskit")
    val presskit: String = "",
    @SerializedName("reddit")
    val reddit: Reddit = Reddit(),
    @SerializedName("webcast")
    val webcast: String = "",
    @SerializedName("wikipedia")
    val wikipedia: String = "",
    @SerializedName("youtube_id")
    val youtubeId: String = ""
): Parcelable

@Parcelize
data class Flickr(
    @SerializedName("original")
    val original: List<String> = listOf(),
    @SerializedName("small")
    val small: List<@RawValue Any> = listOf()
): Parcelable

@Parcelize
data class Patch(
    @SerializedName("large")
    val large: String = "",
    @SerializedName("small")
    val small: String = ""
): Parcelable

@Parcelize
data class Reddit(
    @SerializedName("campaign")
    val campaign: String = "",
    @SerializedName("launch")
    val launch: String = "",
    @SerializedName("media")
    val media: String = "",
    @SerializedName("recovery")
    val recovery: @RawValue Any? = null
): Parcelable