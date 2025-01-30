package com.lauraalvarez.movehabits.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.lauraalvarez.movehabits.data.preferences.UserPreferences
import com.lauraalvarez.movehabits.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val userPreferences: UserPreferences
) : AuthRepository {

    override suspend fun signUp(email: String, password: String, name: String): FirebaseUser? {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        val user = result.user

        user?.let {
            val profileUpdates = userProfileChangeRequest {
                displayName = name
            }
            it.updateProfile(profileUpdates).await()

            val userData = hashMapOf(
                "uid" to it.uid,
                "email" to email,
                "name" to name
            )
            firestore.collection("users").document(it.uid).set(userData).await()

            userPreferences.saveUser(it.uid)
        }

        return user
    }

    override suspend fun login(email: String, password: String): FirebaseUser? {
        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        val user = result.user

        user?.let {
            userPreferences.saveUser(it.uid)
        }

        return user
    }

    override suspend fun resetPassword(email: String): Result<Unit> {
        return try {
            FirebaseAuth.getInstance().sendPasswordResetEmail(email).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun logout() {
        firebaseAuth.signOut()

        // Eliminar usuario de DataStore
        CoroutineScope(Dispatchers.IO).launch {
            userPreferences.clearUser()
        }
    }

    override fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }
}
