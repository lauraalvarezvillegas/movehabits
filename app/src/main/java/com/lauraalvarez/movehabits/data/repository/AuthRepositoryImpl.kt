package com.lauraalvarez.movehabits.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.lauraalvarez.movehabits.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {

    override suspend fun signUp(email: String, password: String, name: String): FirebaseUser? {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        val user = result.user

        user?.let {
            val profileUpdates = userProfileChangeRequest {
                displayName = name
            }
            it.updateProfile(profileUpdates).await()

            // Save user data in Firestore
            val userData = hashMapOf(
                "uid" to it.uid,
                "email" to email,
                "name" to name
            )
            firestore.collection("users").document(it.uid).set(userData).await()
        }

        return user
    }

    override suspend fun login(email: String, password: String): FirebaseUser? {
        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        return result.user
    }

    override fun logout() {
        firebaseAuth.signOut()
    }

    override fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }
}


