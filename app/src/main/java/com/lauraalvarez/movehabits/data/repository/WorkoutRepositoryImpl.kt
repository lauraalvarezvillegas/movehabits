package com.lauraalvarez.movehabits.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.lauraalvarez.movehabits.data.model.Workout
import com.lauraalvarez.movehabits.domain.repository.WorkoutRepository
import com.lauraalvarez.movehabits.utils.FireStoreCollections
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class WorkoutRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : WorkoutRepository {

    override suspend fun addWorkout(workout: Workout) {
        try {
            db.collection(FireStoreCollections.WORKOUTS).add(workout).await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun getWorkouts(userId: String): List<Workout> {
        val snapshot = db.collection(FireStoreCollections.WORKOUTS)
            .whereEqualTo("userId", userId)
            .get()
            .await()

        return snapshot.documents.mapNotNull { document ->
            document.toObject(Workout::class.java)
        }
    }
}
