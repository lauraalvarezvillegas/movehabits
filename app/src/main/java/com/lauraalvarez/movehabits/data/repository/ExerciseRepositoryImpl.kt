package com.lauraalvarez.movehabits.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.lauraalvarez.movehabits.data.enums.ExerciseType
import com.lauraalvarez.movehabits.data.model.Exercise
import com.lauraalvarez.movehabits.domain.repository.ExerciseRepository
import com.lauraalvarez.movehabits.utils.FireStoreCollections
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ExerciseRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : ExerciseRepository {

    override suspend fun getExercises(type: String): List<Exercise> {
        val snapshot = db.collection(FireStoreCollections.EXERCISES)
            .whereEqualTo("tipo", type)
            .get()
            .await()

        return snapshot.documents.mapNotNull { document ->
            document.toObject(Exercise::class.java)
        }
    }
}