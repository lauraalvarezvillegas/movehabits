package com.lauraalvarez.movehabits.ui.navigation

import com.lauraalvarez.movehabits.data.enums.ExerciseType
import kotlinx.serialization.Serializable

interface Route {
    val route: String
}

@Serializable
object Login : Route {
    override val route = "login"
}

@Serializable
object Register : Route {
    override val route = "register"
}

@Serializable
object Home : Route {
    override val route = "home"
}

@Serializable
object Workouts : Route {
    override val route = "workouts"
}

@Serializable
data class NewWorkout(val type: ExerciseType) : Route {
    override val route = "new_workout/${type.name}"
}

@Serializable
object Exercises : Route {
    override val route = "exercises"
}

@Serializable
object Goal : Route {
    override val route = "goal"
}

@Serializable
object Profile : Route {
    override val route = "profile"
}



