package com.betr.android.auth.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.betr.android.auth.ui.navigation.AppNavHost
import com.betr.android.auth.ui.theme.BetrBetaAndroidAuthTheme
import com.betr.android.auth.util.ROUTE_LOGIN
import com.google.firebase.auth.FirebaseAuth

@Composable
fun MainUi(
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_LOGIN,
    firebaseAuth: FirebaseAuth
) {
    BetrBetaAndroidAuthTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AppNavHost(
                navController = navController,
                startDestination = startDestination,
                firebaseAuth = firebaseAuth
            )
        }
    }
}