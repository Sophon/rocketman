package com.example.rocketman.company

import androidx.room.TypeConverter

class CompanyTypeConverter {

    @TypeConverter
    fun fromHqToString(hq: Headquarters): String {
        return hq.toString()
    }

    @TypeConverter
    fun fromStringToHq(string: String): Headquarters {
        val array = string.split("\n")

        return Headquarters(
            address = array[0],
            city =  array[1],
            state = array[2]
        )
    }

    @TypeConverter
    fun fromLinksToString(links: Links): String {
        return links.toString()
    }

    @TypeConverter
    fun fromStringToLinks(string: String): Links {
        val array = string.split(";")

        return Links(
            elonTwitter = array[0],
            flickr = array[1],
            twitter = array[2],
            website = array[3]
        )
    }

}