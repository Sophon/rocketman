package com.example.rocketman.features.company

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Company(
    @SerializedName("ceo")
    val ceo: String = "",
    @SerializedName("coo")
    val coo: String = "",
    @SerializedName("cto")
    val cto: String = "",
    @SerializedName("cto_propulsion")
    val ctoPropulsion: String = "",
    @SerializedName("employees")
    val employees: Int = 0,
    @SerializedName("founded")
    val founded: Int = 0,
    @SerializedName("founder")
    val founder: String = "",
    @SerializedName("headquarters")
    val headquarters: Headquarters = Headquarters(),
    @PrimaryKey
    @SerializedName("id")
    val id: String = "",
    @SerializedName("launch_sites")
    val launchSites: Int = 0,
    @SerializedName("links")
    val links: Links = Links(),
    @SerializedName("name")
    val name: String = "",
    @SerializedName("summary")
    val summary: String = "",
    @SerializedName("test_sites")
    val testSites: Int = 0,
    @SerializedName("valuation")
    val valuation: Long = 0,
    @SerializedName("vehicles")
    val vehicles: Int = 0
)

data class Headquarters(
    @SerializedName("address")
    val address: String = "",
    @SerializedName("city")
    val city: String = "",
    @SerializedName("state")
    val state: String = ""
) {
    override fun toString(): String {
        return "$address\n$city\n$state"
    }
}

data class Links(
    @SerializedName("elon_twitter")
    val elonTwitter: String = "",
    @SerializedName("flickr")
    val flickr: String = "",
    @SerializedName("twitter")
    val twitter: String = "",
    @SerializedName("website")
    val website: String = ""
) {
    override fun toString(): String {
        return "$elonTwitter;$flickr;$twitter;$website"
    }
}