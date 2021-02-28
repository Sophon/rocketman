package com.example.rocketman.features.rocket.list

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.rocketman.RocketManDB
import com.example.rocketman.features.rocket.RocketDao
import com.example.rocketman.features.rocket.RocketRepo
import com.nhaarman.mockitokotlin2.atLeast
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class RocketListVMTest {

    private val dao: RocketDao = Mockito.spy(
        Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            RocketManDB::class.java
        )
            .allowMainThreadQueries()
            .build()
            .rocketDao()
    )

    private var sut: RocketListVM = RocketListVM(RocketRepo(mock(), dao))


    @Test
    fun toggleCallsDb() {
        sut.toggleActiveOnly(false)

        verify(dao, atLeast(1)).getRockets()
    }
}