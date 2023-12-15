package com.betr.android.auth.ui.feature.register

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.betr.android.auth.R
import com.betr.android.auth.entity.AuthRequest
import com.betr.android.auth.ui.feature.login.LoginUiState
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

@Composable
fun RegisterScreen(
    auth: FirebaseAuth,
    onNavigateToHome: () -> Unit = { },
    onNavigateToLogin: () -> Unit = { }
) {

    val uiState by remember { mutableStateOf(LoginUiState()) }
    val context = LocalContext.current

    RegisterUi(
        state = uiState,
        onLoginAction = onNavigateToLogin,
        onRegisterAction = {
            registerWithEmailAndPassword(
                auth = auth,
                request = uiState.buildAuthRequest(),
            ) { result ->
                if (result.isSuccessful) {
                    onNavigateToHome()
                } else {
                    Toast.makeText(
                        context,
                        result.exception?.message ?: "Register Failed",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
        }
    )

}

@Composable
private fun RegisterUi(
    state: LoginUiState,
    onLoginAction: () -> Unit = { },
    onRegisterAction: () -> Unit = { },
) {
    val scrollableState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollableState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.str_register),
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = state.email,
            onValueChange = state::onEmailChange,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = stringResource(R.string.str_email)
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.str_email)
                )
            }
        )
        OutlinedTextField(
            value = state.password,
            onValueChange = state::onPasswordChange,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = stringResource(R.string.str_password)
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.str_password)
                )
            },
            trailingIcon = {
                IconButton(onClick = state::onChangePasswordVisibility) {
                    Icon(
                        imageVector = if (state.showPassword) {
                            Icons.Rounded.VisibilityOff
                        } else {
                            Icons.Rounded.Visibility
                        },
                        contentDescription = ""
                    )
                }
            },
            visualTransformation = if (state.showPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onRegisterAction,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(text = stringResource(id = R.string.str_register))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.str_msg_login)
            )
            TextButton(onClick = onLoginAction) {
                Text(
                    text = stringResource(R.string.str_login_here)
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun RegisterUiPreview() {
    RegisterUi(state = LoginUiState())
}

private fun registerWithEmailAndPassword(
    auth: FirebaseAuth,
    request: AuthRequest,
    onComplete: (Task<AuthResult>) -> Unit,
) {
    auth.createUserWithEmailAndPassword(request.email, request.password)
        .addOnCompleteListener { result ->
            onComplete(result)
        }
}