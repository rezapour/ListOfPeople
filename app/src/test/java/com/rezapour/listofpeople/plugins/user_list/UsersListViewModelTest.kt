package com.rezapour.listofpeople.plugins.user_list

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.rezapour.listofpeople.data.exception.DataProviderException
import com.rezapour.listofpeople.data.repository.UserRepository
import com.rezapour.listofpeople.plugins.users_list.UsersListViewModel
import com.rezapour.listofpeople.util.MainCoroutineRule
import com.rezapour.listofpeople.util.SampleDataFactory
import com.rezapour.listofpeople.util.UiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class UsersListViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: UsersListViewModel
    private lateinit var repository: UserRepository

    @Before
    fun setUp() {
        repository = mock()
        viewModel = UsersListViewModel(repository)
    }

    @Test
    fun `load data return house list when response is successful`() = runTest {
        runBlocking() {
            whenever(repository.getCustomers()).thenReturn(SampleDataFactory.getCustomers())
        }
        viewModel.loadData()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.test {
                assertThat(awaitItem()).isInstanceOf(UiState.Success::class.java)
            }
        }
    }

    @Test
    fun `load data return Error when response is DataProviderException`() = runTest {
        runBlocking() {
            whenever(repository.getCustomers()).thenThrow(DataProviderException::class.java)
        }
        viewModel.loadData()
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.test {
                assertThat(awaitItem()).isInstanceOf(UiState.Error::class.java)
            }
        }
    }

    @Test
    fun `load data return Error when response is Exception`() = runTest {
        runBlocking() {
            whenever(repository.getCustomers()).thenThrow(RuntimeException::class.java)
        }
        viewModel.loadData()
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.test {
                assertThat(awaitItem()).isInstanceOf(UiState.DefaultError::class.java)
            }
        }
    }
}