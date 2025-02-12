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
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import com.lauraalvarez.movehabits.R
import com.lauraalvarez.movehabits.ui.addworkout.NewWorkoutScreen
import com.lauraalvarez.movehabits.ui.bottombar.CustomBottomBar
import com.lauraalvarez.movehabits.ui.exercises.ExercisesScreen
import com.lauraalvarez.movehabits.ui.workouts.WorkoutsScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            val packagePrefix = stringResource(R.string.navigation_package_route)
            val currentBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = currentBackStackEntry?.destination?.route
            val baseRoute = currentRoute?.removePrefix(packagePrefix)?.substringBefore("/")


            if (baseRoute !in listOf(
                    Login::class.simpleName,
                    Register::class.simpleName,
                    NewWorkout::class.simpleName,
                    Exercises::class.simpleName
                )
            ) {
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
                WorkoutsScreen(navController)
            }

            composable<NewWorkout> { backStackEntry ->
                val workout = backStackEntry.toRoute<NewWorkout>()
                NewWorkoutScreen(
                    selectedWorkoutType = workout.type,
                    navController
                )
            }

            composable<Exercises> { backStackEntry ->
                val exercises = backStackEntry.toRoute<Exercises>()
                ExercisesScreen(type = exercises.type)
            }



            /*
            composable(route = Goal::class.simpleName!!) {
                GoalsScreen()
            }
            composable(route = Profile::class.simpleName!!) {
                ProfileScreen()
            }*/
        }
    }
}






