package com.example.rocketman.rocket
import com.google.gson.annotations.SerializedName


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
    @SerializedName("diameter")
    val diameter: Diameter = Diameter(),
    @SerializedName("engines")
    val engines: Engines = Engines(),
    @SerializedName("first_flight")
    val firstFlight: String = "",
    @SerializedName("first_stage")
    val firstStage: FirstStage = FirstStage(),
    @SerializedName("flickr_images")
    val flickrImages: List<String> = listOf(),
    @SerializedName("height")
    val height: Height = Height(),
    @SerializedName("id")
    val id: String = "",
    @SerializedName("landing_legs")
    val landingLegs: LandingLegs = LandingLegs(),
    @SerializedName("mass")
    val mass: Mass = Mass(),
    @SerializedName("name")
    val name: String = "",
    @SerializedName("payload_weights")
    val payloadWeights: List<PayloadWeight> = listOf(),
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
)

data class Diameter(
    @SerializedName("feet")
    val feet: Double = 0.0,
    @SerializedName("meters")
    val meters: Double = 0.0
)

data class Engines(
    @SerializedName("engine_loss_max")
    val engineLossMax: Int = 0,
    @SerializedName("isp")
    val isp: Isp = Isp(),
    @SerializedName("layout")
    val layout: String = "",
    @SerializedName("number")
    val number: Int = 0,
    @SerializedName("propellant_1")
    val propellant1: String = "",
    @SerializedName("propellant_2")
    val propellant2: String = "",
    @SerializedName("thrust_sea_level")
    val thrustSeaLevel: ThrustSeaLevel = ThrustSeaLevel(),
    @SerializedName("thrust_to_weight")
    val thrustToWeight: Double = 0.0,
    @SerializedName("thrust_vacuum")
    val thrustVacuum: ThrustVacuum = ThrustVacuum(),
    @SerializedName("type")
    val type: String = "",
    @SerializedName("version")
    val version: String = ""
)

data class FirstStage(
    @SerializedName("burn_time_sec")
    val burnTimeSec: Int = 0,
    @SerializedName("engines")
    val engines: Int = 0,
    @SerializedName("fuel_amount_tons")
    val fuelAmountTons: Int = 0,
    @SerializedName("reusable")
    val reusable: Boolean = false,
    @SerializedName("thrust_sea_level")
    val thrustSeaLevel: ThrustSeaLevel = ThrustSeaLevel(),
    @SerializedName("thrust_vacuum")
    val thrustVacuum: ThrustVacuum = ThrustVacuum()
)

data class Height(
    @SerializedName("feet")
    val feet: Double = 0.0,
    @SerializedName("meters")
    val meters: Int = 0
)

data class LandingLegs(
    @SerializedName("material")
    val material: String = "",
    @SerializedName("number")
    val number: Int = 0
)

data class Mass(
    @SerializedName("kg")
    val kg: Int = 0,
    @SerializedName("lb")
    val lb: Int = 0
)

data class PayloadWeight(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("kg")
    val kg: Int = 0,
    @SerializedName("lb")
    val lb: Int = 0,
    @SerializedName("name")
    val name: String = ""
)

data class SecondStage(
    @SerializedName("burn_time_sec")
    val burnTimeSec: Int = 0,
    @SerializedName("engines")
    val engines: Int = 0,
    @SerializedName("fuel_amount_tons")
    val fuelAmountTons: Int = 0,
    @SerializedName("payloads")
    val payloads: Payloads = Payloads(),
    @SerializedName("reusable")
    val reusable: Boolean = false,
    @SerializedName("thrust")
    val thrust: Thrust = Thrust()
)

data class Isp(
    @SerializedName("sea_level")
    val seaLevel: Int = 0,
    @SerializedName("vacuum")
    val vacuum: Int = 0
)

data class ThrustSeaLevel(
    @SerializedName("kN")
    val kN: Int = 0,
    @SerializedName("lbf")
    val lbf: Int = 0
)

data class ThrustVacuum(
    @SerializedName("kN")
    val kN: Int = 0,
    @SerializedName("lbf")
    val lbf: Int = 0
)

data class Payloads(
    @SerializedName("composite_fairing")
    val compositeFairing: CompositeFairing = CompositeFairing(),
    @SerializedName("option_1")
    val option1: String = ""
)

data class Thrust(
    @SerializedName("kN")
    val kN: Int = 0,
    @SerializedName("lbf")
    val lbf: Int = 0
)

data class CompositeFairing(
    @SerializedName("diameter")
    val diameter: Diameter = Diameter(),
    @SerializedName("height")
    val height: Height = Height()
)