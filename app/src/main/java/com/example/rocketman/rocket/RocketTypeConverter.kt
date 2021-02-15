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
}