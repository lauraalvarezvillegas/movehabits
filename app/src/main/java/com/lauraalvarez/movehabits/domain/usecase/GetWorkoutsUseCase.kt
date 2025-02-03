package com.lauraalvarez.movehabits.domain.usecase

import com.lauraalvarez.movehabits.data.model.Workout
import com.lauraalvarez.movehabits.domain.repository.WorkoutRepository
import javax.inject.Inject

class GetWorkoutsUseCase @Inject constructor(
    private val workoutRepository: WorkoutRepository
) {

    suspend fun execute(userId: String): List<Workout> {
        return workoutRepository.getWorkouts(userId)
    }
}
