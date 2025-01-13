package com.lauraalvarez.movehabits.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lauraalvarez.movehabits.ui.home.HomeScreen
import com.lauraalvarez.movehabits.ui.login.LoginScreen
import com.lauraalvarez.movehabits.ui.signup.SignUpScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Login // first screen when the app starts
    ) {
        composable<Login> {
            LoginScreen(
                loginViewModel = hiltViewModel(),
                onNavigateToHome = { navController.navigate(Home) },
                onNavigateToRegister = { navController.navigate(Register) }
            )
        }
        composable<Home> {
            HomeScreen()
        }
        composable<Register> {
            SignUpScreen()
        }
    }
}



