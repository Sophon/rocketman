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
    fun fromEnginesToString(engines: Engines?): String? {
        engines?.let {
            engines.apply {
                return "$engineLossMax" +
                        "engine" + fromIspToString(isp) +
                        "engine" + layout +
                        "engine" + "$number" +
                        "engine" + propellant1 +
                        "engine" + propellant2 +
                        "engine" + fromThrustSeaLevelToString(thrustSeaLevel) +
                        "engine" + "$thrustToWeight" +
                        "engine" + fromThrustVacuumToString(thrustVacuum) +
                        "engine" + type +
                        "engine" + version
            }
        }
        return null
    }

    @TypeConverter
    fun fromStringToEngines(str: String?): Engines? {
        str?.let {
            str.split("engine").also {
                val isp = fromStringToIsp(it[1])
                val thrustSeaLevel = fromStringToThrustSeaLevel(it[6])
                val thrustVacuum = fromStringToThrustVacuum(it[8])
                return if(isp != null && thrustSeaLevel != null && thrustVacuum != null) {
                    Engines(
                        engineLossMax = it[0].toInt(),
                        isp = isp,
                        layout = it[2],
                        number = it[3].toInt(),
                        propellant1 = it[4],
                        propellant2 = it[5],
                        thrustSeaLevel = thrustSeaLevel,
                        thrustToWeight = it[7].toDouble(),
                        thrustVacuum = thrustVacuum,
                        type = it[9],
                        version = it[10]
                    )
                } else {
                    null
                }
            }
        }
        return null
    }

    @TypeConverter
    fun fromFirstStageToString(firstStage: FirstStage?): String? {
        firstStage?.let {
            firstStage.apply {
                return "$burnTimeSec" +
                        "stage" + "$engines" +
                        "stage" + "$fuelAmountTons" +
                        "stage" + (if(reusable) "1" else "0") +
                        "stage" +  fromThrustSeaLevelToString(thrustSeaLevel) +
                        "stage" + fromThrustVacuumToString(thrustVacuum)
            }
        }
        return null
    }

    @TypeConverter
    fun fromStringToFirstStage(str: String?): FirstStage? {
        str?.let {
            str.split("stage").also {
                val thrustSeaLevel = fromStringToThrustSeaLevel(it[4])
                val thrustVacuum = fromStringToThrustVacuum(it[5])
                return if(thrustSeaLevel != null && thrustVacuum != null) {
                    FirstStage(
                        burnTimeSec = it[0].toInt(),
                        engines = it[1].toInt(),
                        fuelAmountTons = it[2].toDouble(),
                        reusable = it[3] == "1",
                        thrustSeaLevel = thrustSeaLevel,
                        thrustVacuum = thrustVacuum
                    )
                } else {
                    null
                }
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
    fun fromLadingLegsToString(landingLegs: LandingLegs?): String? {
        landingLegs?.let {
            return "${landingLegs.material}landingLegs${landingLegs.number}"
        }
        return null
    }

    @TypeConverter
    fun fromStringToLandingLegs(str: String?): LandingLegs? {
        str?.let {
            str.split("landingLegs").also {
                return LandingLegs(
                    material = it[0],
                    number = it[1].toInt()
                )
            }
        }
        return null
    }

    @TypeConverter
    fun fromMassToString(mass: Mass?): String? {
        mass?.let {
            return "${mass.kg}mass${mass.lb}"
        }
        return null
    }

    @TypeConverter
    fun fromStringToMass(str: String?): Mass? {
        str?.let {
            str.split("mass").also {
                return Mass(
                    kg = it[0].toInt(),
                    lb = it[1].toInt()
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
    fun fromSecondStageToString(secondStage: SecondStage?): String? {
        secondStage?.let {
            secondStage.apply {
                return "$burnTimeSec" +
                        "secondStage" + "$engines" +
                        "secondStage" + "$fuelAmountTons" +
                        "secondStage" + fromPayloadsToString(payloads) +
                        "secondStage" + (if(reusable) "1" else "0") +
                        "secondStage" + fromThrustToString(thrust)
            }
        }
        return null
    }

    @TypeConverter
    fun fromStringToSecondStage(str: String?): SecondStage? {
        str?.let {
            str.split("secondStage").also {
                val payloads = fromStringToPayloads(it[3])
                val thrust = fromStringToThrust(it[5])
                return if(payloads != null && thrust != null) {
                    return SecondStage(
                        burnTimeSec = it[0].toInt(),
                        engines = it[1].toInt(),
                        fuelAmountTons = it[2].toDouble(),
                        payloads = payloads,
                        reusable = it[4] == "1",
                        thrust = thrust
                    )
                } else {
                    null
                }
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
    fun fromThrustSeaLevelToString(thrustSeaLevel: ThrustSeaLevel?): String? {
        thrustSeaLevel?.let {
            return "${thrustSeaLevel.kN}thrustSeaLevel${thrustSeaLevel.lbf}"
        }
        return null
    }

    @TypeConverter
    fun fromStringToThrustSeaLevel(str: String?): ThrustSeaLevel? {
        str?.let {
            str.split("thrustSeaLevel").also {
                return ThrustSeaLevel(
                    kN = str[0].toInt(),
                    lbf = str[1].toInt()
                )
            }
        }
        return null
    }

    @TypeConverter
    fun fromThrustVacuumToString(thrustVacuum: ThrustVacuum?): String? {
        thrustVacuum?.let {
            return "${thrustVacuum.kN}thrustVacuum${thrustVacuum.lbf}"
        }
        return null
    }

    @TypeConverter
    fun fromStringToThrustVacuum(str: String?): ThrustVacuum? {
        str?.let {
            str.split("thrustVacuum").also {
                return ThrustVacuum(
                    kN = str[0].toInt(),
                    lbf = str[1].toInt()
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