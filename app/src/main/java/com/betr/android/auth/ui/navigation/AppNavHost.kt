package com.betr.android.auth.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.betr.android.auth.ui.feature.login.LoginScreen
import com.betr.android.auth.util.ROUTE_HOME
import com.betr.android.auth.util.ROUTE_LOGIN
import com.betr.android.auth.util.ROUTE_REGISTER
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
    firebaseAuth: FirebaseAuth
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        composable(
            route = ROUTE_LOGIN
        ) {
            LoginScreen(
                auth = firebaseAuth,
                onNavigateToRegister = { },
                onNavigateToHome = { }
            )
        }

        composable(
            route = ROUTE_REGISTER
        ) {

        }

        composable(
            route = ROUTE_HOME
        ) {

        }

    }
}