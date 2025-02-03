package com.lauraalvarez.movehabits.domain.usecase

import com.lauraalvarez.movehabits.data.model.Workout
import com.lauraalvarez.movehabits.domain.repository.WorkoutRepository
import javax.inject.Inject

class AddWorkoutUseCase @Inject constructor(
    private val workoutRepository: WorkoutRepository
) {

    suspend fun execute(workout: Workout) {
        workoutRepository.addWorkout(workout)
    }
}