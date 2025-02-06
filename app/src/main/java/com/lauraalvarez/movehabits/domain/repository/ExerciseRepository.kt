package com.lauraalvarez.movehabits.domain.repository

import com.lauraalvarez.movehabits.data.model.Exercise

interface ExerciseRepository {
    suspend fun getExercises(type: String): List<Exercise>
}