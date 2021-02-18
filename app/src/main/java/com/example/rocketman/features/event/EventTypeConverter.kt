package com.example.rocketman.features.event

import androidx.room.TypeConverter

class EventTypeConverter {

    @TypeConverter
    fun fromLinksToString(links: Links?): String? {
        return links?.article
    }

    @TypeConverter
    fun fromStringToLinks(str: String?): Links? {
        return str?.let {
            Links(it)
        }
    }
}