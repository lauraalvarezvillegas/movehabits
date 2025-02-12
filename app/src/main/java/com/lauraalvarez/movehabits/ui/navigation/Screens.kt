package com.lauraalvarez.movehabits.ui.navigation

import com.lauraalvarez.movehabits.data.enums.ExerciseType
import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object Register

@Serializable
object Home

@Serializable
object Workouts

@Serializable
object Goal

@Serializable
object Profile

@Serializable
data class NewWorkout(val type: ExerciseType)

@Serializable
data class Exercises(val type: ExerciseType)


