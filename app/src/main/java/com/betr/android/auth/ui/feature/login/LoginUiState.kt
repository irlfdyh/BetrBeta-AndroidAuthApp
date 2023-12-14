package com.betr.android.auth.ui.feature.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.betr.android.auth.entity.AuthRequest

class LoginUiState {

    private var _email by mutableStateOf("")
    val email get() = _email

    private var _password by mutableStateOf("")
    val password get() = _password

    private var _showPassword by mutableStateOf(false)
    val showPassword get() = _showPassword

    fun onEmailChange(newValue: String) {
        _email = newValue
    }

    fun onPasswordChange(newValue: String) {
        _password = newValue
    }

    fun onChangePasswordVisibility() {
        _showPassword = !_showPassword
    }

    fun buildAuthRequest(): AuthRequest {
        return AuthRequest(
            email = _email,
            password = _password
        )
    }

}