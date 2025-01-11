package com.lauraalvarez.movehabits.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lauraalvarez.movehabits.ui.home.HomeScreen
import com.lauraalvarez.movehabits.ui.login.LoginScreen

@Composable
fun NavigationWrapper(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Login
    ) {
        /*
        composable<Login> {
            LoginScreen { navController.navigate(Home)}
        }

        composable<Home> {
            HomeScreen { navController.navigate(Home)}
        }*/
    }
}