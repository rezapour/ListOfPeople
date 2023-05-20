package com.rezapour.listofpeople.data.network.impl

import com.google.common.io.Resources
import com.google.common.truth.Truth.assertThat
import com.rezapour.listofpeople.R
import com.rezapour.listofpeople.data.exception.DataProviderException
import com.rezapour.listofpeople.data.network.NetworkDataProvider
import com.rezapour.listofpeople.data.network.api_fake.RetrofitBuilderMock
import com.rezapour.listofpeople.data.network.mapper.NetworkDataMapper
import com.rezapour.listofpeople.util.MainCoroutineRule
import com.rezapour.listofpeople.util.SampleDataFactory
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.File
import java.io.InputStream
import java.net.HttpURLConnection


class NetworkDataProviderImplTest {
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var mockWebServer: MockWebServer
    lateinit var dataProvider: NetworkDataProvider

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()

        dataProvider = NetworkDataProviderImpl(
            RetrofitBuilderMock.provideApiService(mockWebServer),
            NetworkDataMapper()
        )
    }

    @After
    fun destroy() {
        mockWebServer.shutdown()
    }

    private fun response(fileName: String): String {
        val inputStreamUser: InputStream =
            File(Resources.getResource(fileName).toURI()).inputStream()
        return inputStreamUser.bufferedReader().use { it.readText() }
    }

    @Test
    fun `getCustomer response list of Users when run successfully`() {
        val responseTest = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(response("UserList.json"))

        mockWebServer.enqueue(responseTest)

        runTest {
            val response = dataProvider.getCustomers()
            assertThat(response).isEqualTo(SampleDataFactory.getCustomers())
        }
    }

    @Test
    fun `getCustomers throws internet connection exception when api call is failed`() {
        val responseTest = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)

        mockWebServer.enqueue(responseTest)

        val messageId = Assert.assertThrows(DataProviderException::class.java) {
            runTest {
                dataProvider.getCustomers()
            }
        }.messageId
        assertEquals(R.string.error_internet_connection, messageId)
    }

    @Test
    fun `getCustomers throws access denied when response code is 400 range`() {
        val responseTest = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
            .setBody(response("UserList.json"))

        mockWebServer.enqueue(responseTest)


        val messageId = Assert.assertThrows(DataProviderException::class.java) {
            runTest {
                dataProvider.getCustomers()
            }
        }.messageId
        assertEquals(R.string.error_access_denied, messageId)
    }

    @Test
    fun `getCustomers throws server error when response code is 500 range`() {
        val responseTest = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR)
            .setBody(response("UserList.json"))

        mockWebServer.enqueue(responseTest)

        val messageId = Assert.assertThrows(DataProviderException::class.java) {
            runTest {
                dataProvider.getCustomers()
            }
        }.messageId
        assertEquals(R.string.error_server_error, messageId)
    }

    @Test
    fun `getCustomers throws internet connection problem when response code is unknown range`() {
        val responseTest = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_MOVED_PERM)
            .setBody(response("UserList.json"))

        mockWebServer.enqueue(responseTest)

        val messageId = Assert.assertThrows(DataProviderException::class.java) {
            runTest {
                dataProvider.getCustomers()
            }
        }.messageId
        assertEquals(R.string.error_internet_connection, messageId)
    }

    @Test
    fun `getUserDetail response UserDetail when run successfully`() {
        val responseTest = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(response("UserDetail.json"))

        mockWebServer.enqueue(responseTest)

        runTest {
            val response = dataProvider.getUser(1)
            assertThat(response).isEqualTo(SampleDataFactory.getUserDetail())
        }
    }

    @Test
    fun `getUserDetail throws internet connection exception when api call is failed`() {
        val responseTest = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)

        mockWebServer.enqueue(responseTest)

        val messageId = Assert.assertThrows(DataProviderException::class.java) {
            runTest {
                dataProvider.getUser(1)
            }
        }.messageId
        assertEquals(R.string.error_internet_connection, messageId)
    }

    @Test
    fun `getUserDetail throws access denied when response code is 400 range`() {
        val responseTest = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
            .setBody(response("UserDetail.json"))

        mockWebServer.enqueue(responseTest)


        val messageId = Assert.assertThrows(DataProviderException::class.java) {
            runTest {
                dataProvider.getUser(1)
            }
        }.messageId
        assertEquals(R.string.error_access_denied, messageId)
    }

    @Test
    fun `getUserDetail throws server error when response code is 500 range`() {
        val responseTest = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR)
            .setBody(response("UserDetail.json"))

        mockWebServer.enqueue(responseTest)

        val messageId = Assert.assertThrows(DataProviderException::class.java) {
            runTest {
                dataProvider.getUser(1)
            }
        }.messageId
        assertEquals(R.string.error_server_error, messageId)
    }

    @Test
    fun `getUserDetail throws internet connection problem when response code is unknown range`() {
        val responseTest = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_MOVED_PERM)
            .setBody(response("UserDetail.json"))

        mockWebServer.enqueue(responseTest)

        val messageId = Assert.assertThrows(DataProviderException::class.java) {
            runTest {
                dataProvider.getUser(1)
            }
        }.messageId
        assertEquals(R.string.error_internet_connection, messageId)
    }
}