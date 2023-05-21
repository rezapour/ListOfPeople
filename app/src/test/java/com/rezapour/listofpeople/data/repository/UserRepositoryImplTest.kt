package com.rezapour.listofpeople.data.repository


import com.google.common.truth.Truth.assertThat
import com.rezapour.listofpeople.data.exception.DataProviderException
import com.rezapour.listofpeople.data.network.NetworkDataProvider
import com.rezapour.listofpeople.data.repository.impl.UserRepositoryImpl
import com.rezapour.listofpeople.util.MainCoroutineRule
import com.rezapour.listofpeople.util.SampleDataFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class UserRepositoryImplTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var dataProvider: NetworkDataProvider

    private lateinit var repository: UserRepository

    @Before
    fun before() {
        dataProvider = mock()
        repository = UserRepositoryImpl(dataProvider)
    }

    @Test
    fun `getCustomers return Customer list when response is successful`() = runTest {
        whenever(dataProvider.getCustomers()).thenReturn(SampleDataFactory.getCustomers())

        val result = repository.getCustomers()
        assertThat(result).isEqualTo(SampleDataFactory.getCustomers())

        Mockito.verify(dataProvider, times(1)).getCustomers()
    }

    @Test
    fun `getCustomers unsuccessful when response has error`() = runTest {
        whenever(dataProvider.getCustomers()).thenThrow(DataProviderException::class.java)

        Assert.assertThrows(DataProviderException::class.java) {
            runBlocking {
                repository.getCustomers()
            }
        }

        Mockito.verify(dataProvider, times(1)).getCustomers()
    }

    @Test
    fun `getUser return Customer list when response is successful`() = runTest {
        whenever(dataProvider.getUser(1)).thenReturn(SampleDataFactory.getUserDetail())

        val result = repository.getUser(1)
        assertThat(result).isEqualTo(SampleDataFactory.getUserDetail())

        Mockito.verify(dataProvider, times(1)).getUser(1)
    }

    @Test
    fun `getUser unsuccessful when response has error`() = runTest {
        whenever(dataProvider.getUser(1)).thenThrow(DataProviderException::class.java)

        Assert.assertThrows(DataProviderException::class.java) {
            runBlocking {
                repository.getUser(1)
            }
        }

        Mockito.verify(dataProvider, times(1)).getUser(1)
    }
}