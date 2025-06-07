package com.example.mabesogne.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mabesogne.repository.AuthRepository
import com.example.mabesogne.repository.TaskRepository
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel (private val repository: AuthRepository) :ViewModel () {
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun onUsernameChange(newUsername: String) {
        _username.value = newUsername
    }

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    fun logout() {
        _isLoggedIn.value = false
    }

    fun login() {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val result = repository.login(_email.value, _password.value)
            _authState.value = if (result) AuthState.Success else AuthState.Error("Invalid credentials")
        }
    }

    fun signup() {
        viewModelScope.launch {
            val valid = _email.value.contains("@") &&
                    _password.value.length >= 6 &&
                    _password.value.any { it.isDigit() } &&
                    _password.value.any { it.isUpperCase() }
            if (!valid) {
                _authState.value = AuthState.Error("Password must be at least 6 characters, with at least one number and one capital letter")
                return@launch
            }
            val result = repository.signup(_username.value, _email.value, _password.value)
            _authState.value = if (result) AuthState.Success else AuthState.Error("Signup failed")
        }
    }
    sealed class AuthState {
        object Idle : AuthState()
        object Loading : AuthState()
        object Success : AuthState()
        data class Error(val message: String) : AuthState()
    }
}




