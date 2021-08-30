package com.jaime.marvelviewer

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.test.KoinTest
import java.io.File

abstract class BaseTest : KoinTest {

    private lateinit var mockServer: MockWebServer

    @Before
    open fun setup() {
        startMockServer()
    }

//    fun mockNetworkResponseWithFileContent(fileName: String, responseCode: Int) =
//        mockServer.enqueue(
//            MockResponse()
//                .setResponseCode(responseCode)
//                .setBody(getJson(fileName))
//        )

    fun getJson(path: String): String {
        val uri = javaClass.classLoader!!.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }

    private fun startMockServer() {
        mockServer = MockWebServer()
        mockServer.start()
    }


    private fun stopMockServer() {
        mockServer.shutdown()
    }

    @After
    fun tearDown() {
        stopMockServer()
        stopKoin()
    }
}