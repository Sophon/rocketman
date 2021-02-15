package com.example.rocketman.company

import androidx.room.TypeConverter

class CompanyTypeConverter {

    @TypeConverter
    fun fromHqToString(hq: Headquarters?): String? {
        return hq?.toString()
    }

    @TypeConverter
    fun fromStringToHq(str: String?): Headquarters? {
        str?.let {
            val array = str.split("\n")

            return Headquarters(
                address = array[0],
                city =  array[1],
                state = array[2]
            )
        }
        return null
    }

    @TypeConverter
    fun fromLinksToString(links: Links?): String? {
        return links?.toString()
    }

    @TypeConverter
    fun fromStringToLinks(str: String?): Links? {
        str?.let {
            val array = str.split(";")

            return Links(
                elonTwitter = array[0],
                flickr = array[1],
                twitter = array[2],
                website = array[3]
            )
        }
        return null
    }

}