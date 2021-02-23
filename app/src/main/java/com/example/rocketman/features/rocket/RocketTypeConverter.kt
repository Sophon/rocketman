package com.example.rocketman.features.rocket

import androidx.room.TypeConverter

class RocketTypeConverter {

    @TypeConverter
    fun fromDiameterToString(diam: Diameter?): String? {
        return diam?.let {
            "${diam.feet}diameter${diam.meters}"
        }
    }

    @TypeConverter
    fun fromStringToDiameter(str: String?): Diameter? {
        str?.let {
            str.split("diameter").also {
                return Diameter(
                    feet = it[0].toDouble(),
                    meters = it[1].toDouble()
                )
            }
        }
        return null
    }

    @TypeConverter
    fun fromHeightToString(height: Height?): String? {
        height?.let {
            return "${height.feet}height${height.meters}"
        }
        return null
    }

    @TypeConverter
    fun fromStringToHeight(str: String?): Height? {
        str?.let {
            str.split("height").also {
                return Height(
                    feet = it[0].toDouble(),
                    meters = it[1].toDouble()
                )
            }
        }
        return null
    }

    @TypeConverter
    fun fromPayLoadWeightToString(payloadWeight: PayloadWeight?): String? {
        payloadWeight?.let {
            return "${payloadWeight.id} +" +
                    "payloadWeight" + payloadWeight.kg +
                    "payloadWeight" + payloadWeight.lb +
                    "payloadWeight" + payloadWeight.name
        }
        return null
    }

    @TypeConverter
    fun fromStringToPayloadWeight(str: String?): PayloadWeight? {
        str?.let {
            str.split("payloadWeight").also {
                return PayloadWeight(
                    id = it[0],
                    kg = it[1].toInt(),
                    lb = it[2].toInt(),
                    name = it[3]
                )
            }
        }
        return null
    }

    @TypeConverter
    fun fromThrustToString(thrust: Thrust?): String? {
        thrust?.let {
            return "${thrust.kN}thrust${thrust.lbf}"
        }
        return null
    }

    @TypeConverter
    fun fromStringToThrust(str: String?): Thrust? {
        str?.let {
            str.split("thrust").also {
                return Thrust(
                    kN = it[0].toInt(),
                    lbf = it[1].toInt()
                )
            }
        }
        return null
    }

    @TypeConverter
    fun fromPayloadsToString(payloads: Payloads?): String? {
        payloads?.let {
            return fromCompositeFairingToString(payloads.compositeFairing) +
                    "payloads" + payloads.option1
        }
        return null
    }

    @TypeConverter
    fun fromStringToPayloads(str: String?): Payloads? {
        str?.let {
            str.split("payloads").also {
                fromStringToCompositeFairing(it[0])?.let { compositeFairing ->
                    return Payloads(
                        compositeFairing = compositeFairing,
                        option1 = it[1]
                    )
                } ?: return null
            }
        }
        return null
    }

    @TypeConverter
    fun fromCompositeFairingToString(compositeFairing: CompositeFairing?): String? {
        compositeFairing?.let {
            return fromDiameterToString(compositeFairing.diameter) +
                    "compositeFairing" + fromHeightToString(compositeFairing.height)
        }
        return null
    }

    @TypeConverter
    fun fromStringToCompositeFairing(str: String?): CompositeFairing? {
        str?.let {
            str.split("compositeFairing").also {
                val diameter = fromStringToDiameter(it[0])
                val height = fromStringToHeight(it[1])
                return if(diameter != null && height != null) {
                    CompositeFairing(
                        diameter = diameter,
                        height = height
                    )
                } else {
                    null
                }
            }
        }
        return null
    }

    @TypeConverter
    fun fromIspToString(isp: Isp?): String? {
        isp?.let {
            return "${isp.seaLevel}isp${isp.vacuum}"
        }
        return null
    }

    @TypeConverter
    fun fromStringToIsp(str: String?): Isp? {
        str?.let {
            str.split("isp").also {
                return Isp(
                    seaLevel = it[0].toInt(),
                    vacuum = it[1].toInt()
                )
            }
        }
        return null
    }

    @TypeConverter
    fun fromFlickrImagesToString(flickrImages: List<String>?): String? {
        flickrImages?.let {
            var imagesString = ""
            for(image in flickrImages) {
                imagesString += "$image!"
            }
            return imagesString.substring(0, (imagesString.length - 1))
        }
        return null
    }

    @TypeConverter
    fun fromStringToFlickrImages(str: String?): List<String>? {
        str?.let {
            return str.split("!")
        }
        return null
    }

    @TypeConverter
    fun fromPayloadWeightsToString(payloadWeights: List<PayloadWeight>?): String? {
        payloadWeights?.let {
            var weightsString = ""
            for(weight in payloadWeights) {
                weightsString += fromPayLoadWeightToString(weight) + "&"
            }
            return weightsString.substring(0, (weightsString.length - 1))
        }
        return null
    }

    @TypeConverter
    fun fromStringToPayloadWeights(str: String?): List<PayloadWeight>? {
        str?.let {
            val weights = mutableListOf<PayloadWeight>()
            str.split("&").also {
                for(payloadWeight in it) {
                    fromStringToPayloadWeight(payloadWeight)?.let { payloadWeight ->
                        weights.add(payloadWeight)
                    } ?: return null
                }
            }
            return weights
        }
        return null
    }
}