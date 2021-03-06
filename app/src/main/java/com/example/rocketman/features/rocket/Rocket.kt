package com.example.rocketman.features.rocket

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Rocket(
    @SerializedName("active")
    val active: Boolean = false,
    @SerializedName("boosters")
    val boosters: Int = 0,
    @SerializedName("company")
    val company: String = "",
    @SerializedName("cost_per_launch")
    val costPerLaunch: Int = 0,
    @SerializedName("country")
    val country: String = "",
    @SerializedName("description")
    val description: String = "",
    @Embedded
    @SerializedName("diameter")
    val diameter: Diameter = Diameter(),
    @Embedded(prefix = "rocket_engines_")
    @SerializedName("engines")
    val engines: Engines = Engines(),
    @SerializedName("first_flight")
    val firstFlight: String = "",
    @Embedded(prefix = "rocket_firstStage_")
    @SerializedName("first_stage")
    val firstStage: FirstStage = FirstStage(),
    @SerializedName("flickr_images")
    val flickrImages: List<String> = listOf(),
    @Embedded(prefix = "rocket_height_")
    @SerializedName("height")
    val height: Height = Height(),
    @PrimaryKey
    @SerializedName("id")
    val id: String = "",
    @Embedded(prefix = "rocket_secondStage_")
    @SerializedName("landing_legs")
    val landingLegs: LandingLegs = LandingLegs(),
    @Embedded
    @SerializedName("mass")
    val mass: Mass = Mass(),
    @SerializedName("name")
    val name: String = "",
    @SerializedName("payload_weights")
    val payloadWeights: List<PayloadWeight> = listOf(),
    @Embedded
    @SerializedName("second_stage")
    val secondStage: SecondStage = SecondStage(),
    @SerializedName("stages")
    val stages: Int = 0,
    @SerializedName("success_rate_pct")
    val successRatePct: Int = 0,
    @SerializedName("type")
    val type: String = "",
    @SerializedName("wikipedia")
    val wikipedia: String = ""
): Parcelable

@Parcelize
data class Diameter(
    @SerializedName("feet")
    val feet: Double = 0.0,
    @SerializedName("meters")
    val meters: Double = 0.0
): Parcelable

@Parcelize
data class Engines(
    @SerializedName("engine_loss_max")
    val engineLossMax: Int = 0,
    @SerializedName("isp")
    val isp: Isp = Isp(),
    @SerializedName("layout")
    val layout: String? = "",
    @SerializedName("number")
    val number: Int = 0,
    @SerializedName("propellant_1")
    val propellant1: String = "",
    @SerializedName("propellant_2")
    val propellant2: String = "",
    @Embedded(prefix = "rocket_thrustSeaLevel_")
    @SerializedName("thrust_sea_level")
    val thrustSeaLevel: ThrustSeaLevel = ThrustSeaLevel(),
    @SerializedName("thrust_to_weight")
    val thrustToWeight: Double = 0.0,
    @Embedded(prefix = "rocket_thrustVacuum_")
    @SerializedName("thrust_vacuum")
    val thrustVacuum: ThrustVacuum = ThrustVacuum(),
    @SerializedName("type")
    val type: String = "",
    @SerializedName("version")
    val version: String = ""
): Parcelable

@Parcelize
data class FirstStage(
    @SerializedName("burn_time_sec")
    val burnTimeSec: Int = 0,
    @SerializedName("engines")
    val engines: Int = 0,
    @SerializedName("fuel_amount_tons")
    val fuelAmountTons: Double = 0.0,
    @SerializedName("reusable")
    val reusable: Boolean = false,
    @Embedded(prefix = "firstStage_thrustSeaLevel_")
    @SerializedName("thrust_sea_level")
    val thrustSeaLevel: ThrustSeaLevel = ThrustSeaLevel(),
    @Embedded(prefix = "firstStage_thrustVacuum_")
    @SerializedName("thrust_vacuum")
    val thrustVacuum: ThrustVacuum = ThrustVacuum()
): Parcelable

@Parcelize
data class Height(
    @SerializedName("feet")
    val feet: Double = 0.0,
    @SerializedName("meters")
    val meters: Double = 0.0
): Parcelable {
    fun toStringMetric(): String {
        return "Height: $meters m"
    }

    fun toStringImperial(): String {
        return "Height: $feet ft"
    }
}

@Parcelize
data class LandingLegs(
    @SerializedName("material")
    val material: String? = "",
    @SerializedName("number")
    val number: Int = 0
): Parcelable

@Parcelize
data class Mass(
    @SerializedName("kg")
    val kg: Int = 0,
    @SerializedName("lb")
    val lb: Int = 0
): Parcelable {
    fun toStringMetric(): String {
        return "Mass: $kg kg"
    }

    fun toStringImperial(): String {
        return "Mass: $lb lbs"
    }
}

@Parcelize
data class PayloadWeight(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("kg")
    val kg: Int = 0,
    @SerializedName("lb")
    val lb: Int = 0,
    @SerializedName("name")
    val name: String = ""
): Parcelable {
    companion object {
        const val TABLE_NAME = "payloadWeights"
    }
}

@Parcelize
data class SecondStage(
    @SerializedName("burn_time_sec")
    val burnTimeSec: Int = 0,
    @SerializedName("engines")
    val engines: Int = 0,
    @SerializedName("fuel_amount_tons")
    val fuelAmountTons: Double = 0.0,
    @SerializedName("payloads")
    val payloads: Payloads = Payloads(),
    @SerializedName("reusable")
    val reusable: Boolean = false,
    @SerializedName("thrust")
    val thrust: Thrust = Thrust()
): Parcelable

@Parcelize
data class Isp(
    @SerializedName("sea_level")
    val seaLevel: Int = 0,
    @SerializedName("vacuum")
    val vacuum: Int = 0
): Parcelable

@Parcelize
data class ThrustSeaLevel(
    @SerializedName("kN")
    val kN: Int = 0,
    @SerializedName("lbf")
    val lbf: Int = 0
): Parcelable

@Parcelize
data class ThrustVacuum(
    @SerializedName("kN")
    val kN: Int = 0,
    @SerializedName("lbf")
    val lbf: Int = 0
): Parcelable

@Parcelize
data class Payloads(
    @SerializedName("composite_fairing")
    val compositeFairing: CompositeFairing = CompositeFairing(),
    @SerializedName("option_1")
    val option1: String = ""
): Parcelable

@Parcelize
data class Thrust(
    @SerializedName("kN")
    val kN: Int = 0,
    @SerializedName("lbf")
    val lbf: Int = 0
): Parcelable

@Parcelize
data class CompositeFairing(
    @SerializedName("diameter")
    val diameter: Diameter = Diameter(),
    @SerializedName("height")
    val height: Height = Height()
): Parcelable