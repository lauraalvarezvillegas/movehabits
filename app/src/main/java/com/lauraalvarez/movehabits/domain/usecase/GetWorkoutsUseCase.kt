package com.lauraalvarez.movehabits.domain.usecase

import com.google.firebase.firestore.FirebaseFirestore
import com.lauraalvarez.movehabits.data.model.Workout
import com.lauraalvarez.movehabits.utils.FireStoreCollections
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GetWorkoutsUseCase @Inject constructor(private val db: FirebaseFirestore) {

    suspend fun execute(): List<Workout> {
        return try {
            val snapshot = db.collection(FireStoreCollections.WORKOUTS).get().await()
            snapshot.documents.mapNotNull { it.toObject(Workout::class.java) }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
