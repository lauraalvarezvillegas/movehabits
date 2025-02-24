package com.lauraalvarez.movehabits.data.model

import kotlinx.serialization.Serializable


@Serializable
data class WorkoutExercise(
    val exerciseId: String = "",
    val exerciseName: String = "",
    val sets: Int = 0,
    val repetitions: Int = 0,
    val weight: Int = 0,
    val durationSec: Int = 0,
    val completedSets: Int = 0,
    val completed: Boolean = false
)
