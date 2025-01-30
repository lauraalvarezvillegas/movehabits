package com.lauraalvarez.movehabits.domain.usecase

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {
    fun execute() {
        firebaseAuth.signOut()
    }
}