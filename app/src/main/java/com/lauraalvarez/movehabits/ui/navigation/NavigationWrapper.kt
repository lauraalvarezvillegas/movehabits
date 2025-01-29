package com.lauraalvarez.movehabits.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lauraalvarez.movehabits.ui.home.HomeScreen
import com.lauraalvarez.movehabits.ui.login.LoginScreen
import com.lauraalvarez.movehabits.ui.signup.SignUpScreen
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import com.lauraalvarez.movehabits.ui.bottombar.CustomBottomBar
import com.lauraalvarez.movehabits.ui.workouts.WorkoutsScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            val currentBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = currentBackStackEntry?.destination?.route

            if (currentRoute !in listOf(Login::class.simpleName, Register::class.simpleName)) {
                CustomBottomBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Login::class.simpleName!!,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Login::class.simpleName!!) {
                LoginScreen(
                    loginViewModel = hiltViewModel(),
                    onNavigateToHome = { navController.navigate(Home::class.simpleName!!) },
                    onNavigateToRegister = { navController.navigate(Register::class.simpleName!!) }
                )
            }
            composable(route = Register::class.simpleName!!) {
                SignUpScreen(
                    onNavigateToHome = { navController.navigate(Home::class.simpleName!!) }
                )
            }
            composable(route = Home::class.simpleName!!) {
                HomeScreen()
            }

            composable(route = Workouts::class.simpleName!!) {
                WorkoutsScreen()
            }/*
            composable(route = Goal::class.simpleName!!) {
                GoalsScreen()
            }
            composable(route = Profile::class.simpleName!!) {
                ProfileScreen()
            }*/
        }
    }
}






