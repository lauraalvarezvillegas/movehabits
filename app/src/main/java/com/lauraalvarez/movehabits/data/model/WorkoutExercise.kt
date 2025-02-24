package com.lauraalvarez.movehabits.data.model

import kotlinx.serialization.Serializable


@Serializable
data class WorkoutExercise(
    val exerciseId: String = "",
    val exerciseName: String = "",
    val sets: Int = 0,
    val repetitions: Int = 0,
    val weight: Float = 0.0f,
    val durationSec: Int = 0,
    val completedSets: Int = 0,
    val completed: Boolean = false
)
