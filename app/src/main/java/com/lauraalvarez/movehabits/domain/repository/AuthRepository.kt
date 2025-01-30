package com.lauraalvarez.movehabits.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun signUp(email: String, password: String, name: String): FirebaseUser?
    suspend fun login(email: String, password: String): FirebaseUser?
    suspend fun resetPassword(email: String): Result<Unit>
    fun logout()
    fun getCurrentUser(): FirebaseUser?
}