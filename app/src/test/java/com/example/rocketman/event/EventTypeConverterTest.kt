package com.example.rocketman.event

import com.example.rocketman.features.event.EventTypeConverter
import com.example.rocketman.features.event.Links
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsNull.nullValue
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class EventTypeConverterTest {

    private lateinit var sut: EventTypeConverter
    private lateinit var links: Links

    @Before
    fun setUp() {
        sut = EventTypeConverter()
        links = Links(
            article = "https://newrepublic.com/article/159252/noor-one-vampire-ship-heroin-turkey-greece-corruption-scandal"
        )
    }

    @Test
    fun convertsLinksToString() {
        assertThat(
            sut.fromLinksToString(links),
            `is`("https://newrepublic.com/article/159252/noor-one-vampire-ship-heroin-turkey-greece-corruption-scandal")
        )
    }

    @Test
    fun convertsNullLinksToNullString() {
        assertThat(
            sut.fromLinksToString(null),
            `is`(nullValue())
        )
    }

    @Test
    fun convertsStringToLinks() {
        assertThat(
            sut.fromStringToLinks("https://newrepublic.com/article/159252/noor-one-vampire-ship-heroin-turkey-greece-corruption-scandal"),
            `is`(links)
        )
    }

    @Test
    fun convertsNullStringToNullLinks() {
        assertThat(
            sut.fromStringToLinks(null),
            `is`(nullValue())
        )
    }
}