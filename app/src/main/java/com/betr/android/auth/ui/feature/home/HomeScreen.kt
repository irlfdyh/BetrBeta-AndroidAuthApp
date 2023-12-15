package com.betr.android.auth.ui.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.betr.android.auth.R
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreen(
    auth: FirebaseAuth,
    onNavigateToLogin: () -> Unit = { }
) {
    val name by remember {
        mutableStateOf(auth.getUserName())
    }
    HomeUi(
        name = name,
        onSignOutAction = {
            auth.signOut()
            onNavigateToLogin()
        }
    )
}

@Composable
private fun HomeUi(
    name: String,
    onSignOutAction: () -> Unit = { }
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.str_tmp_greeting, name),
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onSignOutAction) {
            Text(text = stringResource(R.string.str_sign_out))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeUiPreview() {
    HomeUi(name = "Eliza")
}

private fun FirebaseAuth.getUserName(): String {
    return this.currentUser?.displayName ?: this.currentUser?.email?.split("@")?.first() ?: "Unknown"
}