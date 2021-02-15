package com.example.rocketman.rocket

import androidx.room.TypeConverter

class RocketTypeConverter {

    @TypeConverter
    fun fromDiameterToString(diam: Diameter): String {
        return "${diam.feet};${diam.meters}"
    }

    @TypeConverter
    fun fromStringToDiameter(str: String): Diameter {
        str.split(";").also {
            return Diameter(
                feet = it[0].toDouble(),
                meters = it[1].toDouble()
            )
        }
    }

    @TypeConverter
    fun fromEnginesToString(engines: Engines): String {
        engines.apply {
            return "$engineLossMax" +
                    ";" + fromIspToString(isp) +
                    ";" + layout +
                    ";" + "$number" +
                    ";" + propellant1 +
                    ";" + propellant2 +
                    ";" + fromThrustSeaLevelToString(thrustSeaLevel) +
                    ";" + "$thrustToWeight" +
                    ";" + fromThrustVacuumToString(thrustVacuum) +
                    ";" + type +
                    ";" + version
        }
    }

    @TypeConverter
    fun fromStringToEngines(str: String): Engines {
        str.split(";").also {
            return Engines(
                engineLossMax = it[0].toInt(),
                isp = fromStringToIsp(it[1]),
                layout = it[2],
                number = it[3].toInt(),
                propellant1 = it[4],
                propellant2 = it[5],
                thrustSeaLevel = fromStringToThrustSeaLevel(it[6]),
                thrustToWeight = it[7].toDouble(),
                thrustVacuum = fromStringToThrustVacuum(it[8]),
                type = it[9],
                version = it[10]
            )
        }
    }

    @TypeConverter
    fun fromFirstStageToString(firstStage: FirstStage): String {
        firstStage.apply {
            return "$burnTimeSec" +
                    ";" + "$engines" +
                    ";" + "$fuelAmountTons" +
                    ";" + (if(reusable) "1" else "0") +
                    ";" +  fromThrustSeaLevelToString(thrustSeaLevel) +
                    ";" + fromThrustVacuumToString(thrustVacuum)
        }
    }

    @TypeConverter
    fun fromStringToFirstStage(str: String): FirstStage {
        str.split(";").also {
            return FirstStage(
                burnTimeSec = it[0].toInt(),
                engines = it[1].toInt(),
                fuelAmountTons = it[2].toDouble(),
                reusable = it[3] == "1",
                thrustSeaLevel = fromStringToThrustSeaLevel(it[4]),
                thrustVacuum = fromStringToThrustVacuum(it[5])
            )
        }
    }

    @TypeConverter
    fun fromHeightToString(height: Height): String {
        return "${height.feet};${height.meters}"
    }

    @TypeConverter
    fun fromStringToHeight(str: String): Height {
        str.split(";").also {
            return Height(
                feet = it[0].toDouble(),
                meters = it[1].toDouble()
            )
        }
    }

    @TypeConverter
    fun fromLadingLegsToString(landingLegs: LandingLegs): String {
        return "${landingLegs.material};${landingLegs.number}"
    }

    @TypeConverter
    fun fromStringToLandingLegs(str: String): LandingLegs {
        str.split(";").also {
            return LandingLegs(
                material = it[0],
                number = it[1].toInt()
            )
        }
    }

    @TypeConverter
    fun fromMassToString(mass: Mass): String {
        return "${mass.kg};${mass.lb}"
    }

    @TypeConverter
    fun fromStringToMass(str: String): Mass {
        str.split(";").also {
            return Mass(
                kg = it[0].toInt(),
                lb = it[1].toInt()
            )
        }
    }

    @TypeConverter
    fun fromPayLoadWeightToString(payloadWeight: PayloadWeight): String {
        return "${payloadWeight.id};${payloadWeight.kg};${payloadWeight.lb};${payloadWeight.name}"
    }

    @TypeConverter
    fun fromStringToPayloadWeight(str: String): PayloadWeight {
        str.split(";").also {
            return PayloadWeight(
                id = it[0],
                kg = it[1].toInt(),
                lb = it[2].toInt(),
                name = it[3]
            )
        }
    }

    @TypeConverter
    fun fromSecondStageToString(secondStage: SecondStage): String {
        secondStage.apply {
            return "$burnTimeSec" +
                    ";" + "$engines" +
                    ";" + "$fuelAmountTons" +
                    ";" + fromPayloadsToString(payloads) +
                    ";" + (if(reusable) "1" else "0") +
                    ";" + fromThrustToString(thrust)
        }
    }

    @TypeConverter
    fun fromStringToSecondStage(str: String): SecondStage {
        str.split(";").also {
            return SecondStage(
                burnTimeSec = it[0].toInt(),
                engines = it[1].toInt(),
                fuelAmountTons = it[2].toDouble(),
                payloads = fromStringToPayloads(it[3]),
                reusable = it[4] == "1",
                thrust = fromStringToThrust(it[5])
            )
        }
    }

    @TypeConverter
    fun fromThrustToString(thrust: Thrust): String {
        return "${thrust.kN};${thrust.lbf}"
    }

    @TypeConverter
    fun fromStringToThrust(str: String): Thrust {
        str.split(";").also {
            return Thrust(
                kN = it[0].toInt(),
                lbf = it[1].toInt()
            )
        }
    }

    @TypeConverter
    fun fromPayloadsToString(payloads: Payloads): String {
        return fromCompositeFairingToString(payloads.compositeFairing) + "${payloads.option1}"
    }

    @TypeConverter
    fun fromStringToPayloads(str: String): Payloads {
        str.split(";").also {
            return Payloads(
                compositeFairing = fromStringToCompositeFairing(it[0]),
                option1 = it[1]
            )
        }
    }

    @TypeConverter
    fun fromCompositeFairingToString(compositeFairing: CompositeFairing): String {
        return fromDiameterToString(compositeFairing.diameter) +
                fromHeightToString(compositeFairing.height)
    }

    @TypeConverter
    fun fromStringToCompositeFairing(str: String): CompositeFairing {
        str.split(";").also {
            return CompositeFairing(
                diameter = fromStringToDiameter(it[0]),
                height =  fromStringToHeight(it[1])
            )
        }
    }

    @TypeConverter
    fun fromIspToString(isp: Isp): String {
        return "${isp.seaLevel};${isp.vacuum}"
    }

    @TypeConverter
    fun fromStringToIsp(str: String): Isp {
        str.split(";").also {
            return Isp(
                seaLevel = it[0].toInt(),
                vacuum = it[1].toInt()
            )
        }
    }

    @TypeConverter
    fun fromThrustSeaLevelToString(thrustSeaLevel: ThrustSeaLevel): String {
        return "${thrustSeaLevel.kN};${thrustSeaLevel.lbf}"
    }

    @TypeConverter
    fun fromStringToThrustSeaLevel(str: String): ThrustSeaLevel {
        str.split(";").also {
            return ThrustSeaLevel(
                kN = str[0].toInt(),
                lbf = str[1].toInt()
            )
        }
    }

    @TypeConverter
    fun fromThrustVacuumToString(thrustVacuum: ThrustVacuum): String {
        return "${thrustVacuum.kN};${thrustVacuum.lbf}"
    }

    @TypeConverter
    fun fromStringToThrustVacuum(str: String): ThrustVacuum {
        str.split(";").also {
            return ThrustVacuum(
                kN = str[0].toInt(),
                lbf = str[1].toInt()
            )
        }
    }

    @TypeConverter
    fun fromFlickrImagesToString(flickrImages: List<String>): String {
        var imagesString = ""
        for(image in flickrImages) {
            imagesString += "$image;"
        }
        return imagesString.substring(0, (imagesString.length - 1))
    }

    @TypeConverter
    fun fromStringToFlickrImages(str: String): List<String> {
        return str.split(";")
    }

    @TypeConverter
    fun fromPayloadWeightsToString(payloadWeights: List<PayloadWeight>): String {
        var weightsString = ""
        for(weight in payloadWeights) {
            weightsString += fromPayLoadWeightToString(weight) + ";"
        }
        return weightsString.substring(0, (weightsString.length - 1))
    }

    @TypeConverter
    fun fromStringToPayloadWeights(str: String): List<PayloadWeight> {
        val weights = mutableListOf<PayloadWeight>()
        str.split(";").also {
            for(payloadWeight in it) {
                weights.add(fromStringToPayloadWeight(payloadWeight))
            }
        }

        return weights
    }
}