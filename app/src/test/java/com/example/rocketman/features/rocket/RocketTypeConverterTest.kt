package com.example.rocketman.features.rocket

import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsNull.nullValue
import org.junit.Before
import org.junit.Assert.*
import org.junit.Test

class RocketTypeConverterTest {

    private lateinit var sut: RocketTypeConverter

    private lateinit var diameter: Diameter
    private lateinit var height: Height
    private lateinit var payloadWeight: PayloadWeight
    private lateinit var thrust: Thrust
    private lateinit var payloads: Payloads
    private lateinit var compositeFairing: CompositeFairing
    private lateinit var isp: Isp
    private lateinit var flickrImages: List<String>
    private lateinit var payloadWeights: List<PayloadWeight>

    @Before
    fun setUp() {
        sut = RocketTypeConverter()

        diameter = Diameter(6.0, 1.83)
        height = Height(6.0, 1.83)
        payloadWeight = PayloadWeight(
            "payloadWeight",
            50,
            110,
            "nameOfPayloadWeight"
        )
        thrust = Thrust(
            100,
            23
        )
        compositeFairing = CompositeFairing(
            diameter,
            height
        )
        payloads = Payloads(
            compositeFairing,
            "option1"
        )
        isp = Isp(
            69,
            79
        )
        flickrImages = listOf(
            "www.google.com",
            "www.reddit.com"
        )
        payloadWeights = listOf(
            PayloadWeight()

        )
    }

    //region Diameter
    @Test
    fun convertsDiameterToString() {
        assertThat(sut.fromDiameterToString(diameter), `is`("${diameter.feet}diameter${diameter.meters}"))
    }

    @Test
    fun convertsNullDiameterToNullString() {
        assertThat(sut.fromDiameterToString(null), `is`(nullValue()))
    }

    @Test
    fun convertsStringToDiameter() {
        assertThat(sut.fromStringToDiameter("${diameter.feet}diameter${diameter.meters}"), `is`(diameter))
    }

    @Test
    fun convertsNullStringToNullDiameter() {
        assertThat(sut.fromStringToDiameter(null), `is`(nullValue()) )
    }
    //endregion

    //region Height
    @Test
    fun convertsHeightToString() {
        assertThat(sut.fromHeightToString(height), `is`("${height.feet}height${height.meters}"))
    }

    @Test
    fun convertsNullHeightToNull() {
        assertThat(sut.fromHeightToString(null), `is`(nullValue()))
    }

    @Test
    fun convertsStringToHeight() {
        assertThat(sut.fromStringToHeight("${height.feet}height${height.meters}"), `is`(height))
    }

    @Test
    fun convertsNullStringToNullHeight() {
        assertThat(sut.fromStringToHeight(null), `is`(nullValue()))
    }
    //endregion

    //region PayloadWeight
    @Test
    fun convertsPayloadWeightToString() {
        assertThat(
            sut.fromPayLoadWeightToString(payloadWeight),
            `is`("${payloadWeight.id}payloadWeightDelim${payloadWeight.kg}payloadWeightDelim${payloadWeight.lb}payloadWeightDelim${payloadWeight.name}")
        )
    }

    @Test
    fun convertsNullPayloadWeightToNullString() {
        assertThat(sut.fromPayLoadWeightToString(null), `is`(nullValue()))
    }

    @Test
    fun convertsStringToPayloadWeight() {
        assertThat(
            sut.fromStringToPayloadWeight("${payloadWeight.id}payloadWeightDelim${payloadWeight.kg}payloadWeightDelim${payloadWeight.lb}payloadWeightDelim${payloadWeight.name}"),
            `is`(payloadWeight)
        )
    }

    @Test
    fun convertsNullStringToNullPayloadWeight() {
        assertThat(sut.fromStringToPayloadWeight(null), `is`(nullValue()))
    }
    //endregion
}