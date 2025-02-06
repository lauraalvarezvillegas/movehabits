package com.lauraalvarez.movehabits.domain.usecase

import com.lauraalvarez.movehabits.data.enums.ExerciseType
import com.lauraalvarez.movehabits.data.model.Exercise
import com.lauraalvarez.movehabits.domain.repository.ExerciseRepository
import javax.inject.Inject

class GetExerciseUseCase @Inject constructor(
    private val exerciseRepository: ExerciseRepository
) {
    suspend fun execute(type: String): List<Exercise>{
        return exerciseRepository.getExercises(type)
    }

}