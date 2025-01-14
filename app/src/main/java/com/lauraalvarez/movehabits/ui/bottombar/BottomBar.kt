package com.lauraalvarez.movehabits.ui.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.lauraalvarez.movehabits.ui.navigation.Goal
import com.lauraalvarez.movehabits.ui.navigation.Home
import com.lauraalvarez.movehabits.ui.navigation.Profile
import com.lauraalvarez.movehabits.ui.navigation.Workouts
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.lauraalvarez.movehabits.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.ui.graphics.ColorFilter


@Composable
fun CustomBottomBar(navController: NavHostController) {
    val bottomBarItems = listOf(
        BottomBarItem("Home", R.drawable.home_icon, Home),
        BottomBarItem("Workouts", R.drawable.workout_icon, Workouts),
        BottomBarItem("Goals", R.drawable.goal_icon, Goal),
        BottomBarItem("Profile", R.drawable.profile_icon, Profile)
    )

    Box(
        modifier = Modifier
            .padding(bottom = 5.dp)
            .fillMaxWidth()
            .background(Color.Transparent)
    ) {
        NavigationBar(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .background(Color.Transparent),
            containerColor = Color.Transparent,
            contentColor = Color.Black,

        ) {
            val currentBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = currentBackStackEntry?.destination

            bottomBarItems.forEach { item ->
                val selected = currentDestination?.route == item.destination::class.simpleName

                NavigationBarItem(
                    icon = {
                        Image(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title,
                            modifier = Modifier.size(24.dp),
                            colorFilter = if (selected) {
                                ColorFilter.tint(colorResource(R.color.original_blue))
                            } else {
                                ColorFilter.tint(Color.Gray)
                            }
                        )
                    },
                    label = {
                        Text(
                            item.title,
                            fontSize = 12.sp
                        )
                    },
                    selected = selected,
                    onClick = {
                        val route = item.destination::class.simpleName!!
                        navController.navigate(route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = colorResource(R.color.original_blue),
                        unselectedIconColor = Color.Gray,
                        selectedTextColor = colorResource(R.color.original_blue),
                        unselectedTextColor = Color.Gray
                    )
                )
            }
        }
    }
}


data class BottomBarItem(
    val title: String,
    val icon: Int,
    val destination: Any
)


