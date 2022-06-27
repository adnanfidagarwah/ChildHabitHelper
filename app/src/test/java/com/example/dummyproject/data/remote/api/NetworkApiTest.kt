package com.example.dummyproject.data.remote.api

import com.example.dummyproject.MainCoroutineRule
import com.example.dummyproject.data.remote.network.NetworkApi
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class NetworkApiTest : ApiAbstract<NetworkApi>() {

    private lateinit var networkApi: NetworkApi

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        networkApi = createService(NetworkApi::class.java)
    }

    @After
    fun tearDown() {
    }


    /**
     * Test Case: Repositories API Call
     */
    @Throws(IOException::class)
    @Test
    fun `test Repositories() returns List of values`() = runBlocking {
        // Given
        enqueueResponse("/repositories_response.json")

        // Invoke
        val response = networkApi.loadRepositories()
        val responseBody = requireNotNull((response.body()))
        mockWebServer.takeRequest()

        // Then
        Truth.assertThat(responseBody.incompleteResults).isEqualTo(false)
        Truth.assertThat(responseBody.items.isEmpty()).isEqualTo(false)
    }


}
