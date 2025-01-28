package com.lauraalvarez.movehabits.data.model

data class WorkoutExercise(
    var workoutExercideId: String,
    var exerciseId : String,
    var secDuration : Int,
    var workoutId: String,
    var exerciseName: String,
    var weight: Float,
    var repetitions: Int,
    var setsNumber: Int,
    var setsCompleted: Int,
    var completed: Boolean


)
