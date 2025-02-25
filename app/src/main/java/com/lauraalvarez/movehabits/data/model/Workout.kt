package com.lauraalvarez.movehabits.data.model

import com.google.firebase.Timestamp
import com.lauraalvarez.movehabits.data.enums.ExerciseType


data class Workout(
    val userId: String = "",
    val type: ExerciseType = ExerciseType.STRENGTH,
    val dateTime: Timestamp = Timestamp.now(),
    val totalDurationSec: Int = 0,
    val restSec: Int = 0,
    val completed: Boolean = false,
    val exercises: List<WorkoutExercise> = emptyList()
) {

    constructor() : this(
        userId = "",
        type = ExerciseType.STRENGTH,
        dateTime = Timestamp.now(),
        totalDurationSec = 0,
        restSec = 0,
        completed = false,
        exercises = emptyList()
    )
}
