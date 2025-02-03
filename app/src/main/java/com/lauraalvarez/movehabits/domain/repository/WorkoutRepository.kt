package com.lauraalvarez.movehabits.domain.repository

import com.lauraalvarez.movehabits.data.model.Workout

interface WorkoutRepository {
    suspend fun addWorkout(workout: Workout)
    suspend fun getWorkouts(userId: String): List<Workout>
}
