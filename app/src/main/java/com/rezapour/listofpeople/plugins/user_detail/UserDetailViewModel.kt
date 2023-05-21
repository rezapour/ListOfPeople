package com.rezapour.listofpeople.plugins.user_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rezapour.listofpeople.data.exception.DataProviderException
import com.rezapour.listofpeople.data.repository.UserRepository
import com.rezapour.listofpeople.models.User
import com.rezapour.listofpeople.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val usersRepository: UserRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<User>> = MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<User>> = _uiState

    fun loadData(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = UiState.Loading
            try {
                _uiState.value = UiState.Success(usersRepository.getUser(userId))
            } catch (e: DataProviderException) {
                _uiState.value = UiState.Error(e.messageId)
            } catch (e: Exception) {
                _uiState.value = UiState.DefaultError
            }
        }
    }
}