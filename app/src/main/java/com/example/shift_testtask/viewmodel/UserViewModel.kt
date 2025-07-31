package com.example.shift_testtask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shift_testtask.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class UserUiState {
    object Loading : UserUiState()
    data class Success(val users: List<com.example.shift_testtask.data.model.Result>) : UserUiState()
    data class Error(val message: String) : UserUiState()
}

class UserViewModel(private val repository: UserRepository): ViewModel() {

    private val _uiState = MutableStateFlow<UserUiState>(UserUiState.Loading)
    val uiState: StateFlow<UserUiState> = _uiState

    init{
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch {
            try {
                val users = repository.getRandomUsers()
                _uiState.value = UserUiState.Success(users)
            } catch (e: Exception) {
                _uiState.value = UserUiState.Error("Ошибка загрузки: ${e.message}")
            }
        }
    }
}