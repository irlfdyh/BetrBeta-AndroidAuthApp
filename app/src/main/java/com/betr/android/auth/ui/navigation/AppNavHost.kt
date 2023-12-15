package com.betr.android.auth.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.betr.android.auth.ui.feature.login.LoginScreen
import com.betr.android.auth.ui.feature.register.RegisterScreen
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
                onNavigateToRegister = {
                    navController.navigate(route = ROUTE_REGISTER)
                },
                onNavigateToHome = {
                    navController.navigate(
                        route = ROUTE_HOME,
                        navOptions = navOptions {
                            popUpTo(
                                route = ROUTE_LOGIN
                            ) {
                                inclusive = true
                            }
                        }
                    )
                }
            )
        }

        composable(
            route = ROUTE_REGISTER
        ) {
            RegisterScreen(
                auth = firebaseAuth,
                onNavigateToHome = {
                    navController.navigate(
                        route = ROUTE_HOME,
                        navOptions = navOptions {
                            popUpTo(
                                route = ROUTE_LOGIN
                            ) {
                                inclusive = true
                            }
                        }
                    )
                },
                onNavigateToLogin = {
                    navController.navigateUp()
                }
            )
        }

        composable(
            route = ROUTE_HOME
        ) {

        }

    }
}