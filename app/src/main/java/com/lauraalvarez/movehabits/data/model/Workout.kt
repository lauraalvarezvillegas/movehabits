package com.lauraalvarez.movehabits.data.model

import com.google.firebase.Timestamp


data class Workout(
    val userId: String = "",
    val type: String = "",
    val dateTime: Timestamp = Timestamp.now(),
    val totalDurationSec: Int = 0,
    val restSec: Int = 0,
    val completed: Boolean = false,
    val exercises: List<Exercise> = emptyList()
)
