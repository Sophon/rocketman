package com.example.rocketman.launch

import androidx.room.TypeConverter

class LaunchTypeConverter {

    @TypeConverter
    fun fromCoreToString(core: Core?): String? {
        core?.let {
            core.apply {
                return "$core" +
                        "coreDelim" + "$flight" +
                        "coreDelim" + if(gridfins) "1" else "0" +
                        "coreDelim" + if(landingAttempt) "1" else "0" +
                        "coreDelim" + if(landingSuccess) "1" else "0" +
                        "coreDelim" + landingType +
                        "coreDelim" + landpad +
                        "coreDelim" + if(legs) "1" else "0" +
                        "coreDelim" + if(reused) "1" else "0"
            }
        }
        return null
    }

    @TypeConverter
    fun fromStringToCore(str: String?): Core? {
        str?.let {
            str.split("coreDelim").also {
                return Core(
                    core = it[0],
                    flight = it[1].toInt(),
                    gridfins = it[2] == "1",
                    landingAttempt = it[3] == "1",
                    landingSuccess = it[4] == "1",
                    landingType = it[5],
                    landpad = it[6],
                    legs = it[7] == "1",
                    reused = it[8] == "1"
                )
            }
        }
        return null
    }

    @TypeConverter
    fun fromRedditToString(reddit: Reddit?): String? {
        reddit?.let {
            return it.campaign +
                    "redditDelim" + it.launch +
                    "redditDelim" + it.media
        }
        return null
    }

    @TypeConverter
    fun fromStringToReddit(str: String?): Reddit? {
        str?.let {
            str.split("redditDelim").also {
                return Reddit(
                    campaign = it[0],
                    launch = it[1],
                    media = it[2],
                    recovery = Any()
                )
            }
        }
        return null
    }

    @TypeConverter
    fun fromPatchToString(patch: Patch?): String? {
        patch?.let {
            return it.large + "patchDelim" + it.small
        }
        return null
    }

    @TypeConverter
    fun fromStringToPatch(str: String?): Patch? {
        str?.let {
            str.split("patchDelim").also {
                return Patch(
                    large = it[0],
                    small = it[1]
                )
            }
        }
        return null
    }

    @TypeConverter
    fun fromFlickrToString(flickr: Flickr?): String? {
        flickr?.let {
            var flickrString = ""
            for(flickrOrig in it.original) {
                flickrString += "$flickrOrig!"
            }
            return flickrString.substring(0, (flickrString.length - 1))
        }
        return null
    }

    @TypeConverter
    fun fromStringToFlickr(str: String?): Flickr? {
        str?.let {
            val flickrOrigs = mutableListOf<String>()
            str.split("!").also {
                for(orig in it) {
                    flickrOrigs.add(orig)
                }

                return Flickr(
                    original = flickrOrigs,
                    small = listOf(Any())
                )
            }
        }
        return null
    }

    @TypeConverter
    fun fromLinksToString(links: Links?): String? {
        links?.let {
            links.apply {
                return article +
                        "linksDelim" + fromFlickrToString(flickr) +
                        "linksDelim" + fromPatchToString(patch) +
                        "linksDelim" + presskit +
                        "linksDelim" + fromRedditToString(reddit) +
                        "linksDelim" + webcast +
                        "linksDelim" + wikipedia +
                        "linksDelim" + youtubeId
            }
        }
        return null
    }

    @TypeConverter
    fun fromStringToLinks(str: String?): Links? {
        str?.let {
            str.split("linksDelim").also {
                val flickr = fromStringToFlickr(it[1])
                val patch = fromStringToPatch(it[2])
                val reddit = fromStringToReddit(it[4])
                return if(flickr != null && patch != null && reddit != null) {
                    Links(
                        article = it[0],
                        flickr = flickr,
                        patch = patch,
                        presskit =  it[3],
                        reddit = reddit,
                        webcast = it[5],
                        wikipedia = it[6],
                        youtubeId = it[7]
                    )
                } else {
                    null
                }
            }
        }
        return null
    }
}