package com.rezapour.listofpeople.plugins.user_detail

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.rezapour.listofpeople.data.exception.DataProviderException
import com.rezapour.listofpeople.data.repository.UserRepository
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
class UserDetailViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: UserDetailViewModel
    private lateinit var repository: UserRepository

    @Before
    fun setUp() {
        repository = mock()
        viewModel = UserDetailViewModel(repository)
    }

    @Test
    fun `load data return house list when response is successful`() = runTest {
        runBlocking() {
            whenever(repository.getUser(1)).thenReturn(SampleDataFactory.getUserDetail())
        }
        viewModel.loadData(1)
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.test {
                Truth.assertThat(awaitItem()).isInstanceOf(UiState.Success::class.java)
            }
        }
    }

    @Test
    fun `load data return Error when response is DataProviderException`() = runTest {
        runBlocking() {
            whenever(repository.getUser(1)).thenThrow(DataProviderException::class.java)
        }
        viewModel.loadData(1)
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.test {
                Truth.assertThat(awaitItem()).isInstanceOf(UiState.Error::class.java)
            }
        }
    }

    @Test
    fun `load data return Error when response is Exception`() = runTest {
        runBlocking() {
            whenever(repository.getUser(1)).thenThrow(RuntimeException::class.java)
        }
        viewModel.loadData(1)
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.test {
                Truth.assertThat(awaitItem()).isInstanceOf(UiState.DefaultError::class.java)
            }
        }
    }
}