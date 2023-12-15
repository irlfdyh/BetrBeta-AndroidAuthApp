package com.betr.android.auth.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.betr.android.auth.ui.theme.BetrBetaAndroidAuthTheme
import com.betr.android.auth.util.ROUTE_HOME
import com.betr.android.auth.util.ROUTE_LOGIN
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser
        val startDestination = if (currentUser != null) ROUTE_HOME else ROUTE_LOGIN

        setContent {
            MainUi(
                startDestination = startDestination,
                firebaseAuth = firebaseAuth
            )
        }

    }

}