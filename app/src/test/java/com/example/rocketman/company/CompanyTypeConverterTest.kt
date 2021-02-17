package com.example.rocketman.company

import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsNull.nullValue
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class CompanyTypeConverterTest {

    private lateinit var sut: CompanyTypeConverter

    private lateinit var hq: Headquarters
    private lateinit var links: Links

    @Before
    fun setUp() {
        sut = CompanyTypeConverter()

        hq = Headquarters(
            address = "Thakurova",
            city = "Praha",
            state = "Czechia"
        )

        links = Links(
            elonTwitter = "twitter.com/elonmusk",
            flickr = "flickr.com/spacex",
            twitter = "twitter.com/spacex",
            website = "www.spacex.com"
        )
    }

    //region Hq
    @Test
    fun convertsHqToString() {
        assertThat(
            sut.fromHqToString(hq),
            `is`("${hq.address}\n${hq.city}\n${hq.state}")
        )
    }

    @Test
    fun convertsNullHqToNullString() {
        assertThat(
            sut.fromHqToString(null),
            `is`(nullValue())
        )
    }

    @Test
    fun convertsStringToHq() {
        assertThat(
            sut.fromStringToHq("Thakurova\nPraha\nCzechia"),
            `is`(hq)
        )
    }

    @Test
    fun convertsNullStringToNullHq() {
        assertThat(
            sut.fromStringToHq(null),
            `is`(nullValue())
        )
    }
    //endregion

    //region Links
    @Test
    fun convertsLinksToString() {
        assertThat(
            sut.fromLinksToString(links),
            `is`("${links.elonTwitter};${links.flickr};${links.twitter};${links.website}")
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
            sut.fromStringToLinks("twitter.com/elonmusk;flickr.com/spacex;twitter.com/spacex;www.spacex.com"),
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