package com.jaime.marvelviewer

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jaime.marvelviewer.repository.SeriesRepository

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin

@RunWith(JUnit4::class)
class RepositoryTests: BaseTest() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repo: SeriesRepository

    @Before
    override fun setup(){
        super.setup()
        startKoin {
            modules(
                configureTestAppComponent("/")
            )
        }
    }

}