package com.example.rocketman.features.rocket.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.rocketman.features.rocket.Repo
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RocketListVMTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var sut: RocketListVM
    private var activeOnlyToggle = false

    private val repo: Repo = mock()

    @Before
    fun setUp() {
        sut = RocketListVM(repo)
        activeOnlyToggle = false
    }

    @Test
    fun toggleActiveOnlyWithTrueCallsRepository() {
        sut.toggleActiveOnly(true)

        verify(repo).getActiveOnlyLocalRockets()
    }
}