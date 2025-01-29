package com.lauraalvarez.movehabits.domain.usecase

import com.google.firebase.auth.FirebaseUser
import com.lauraalvarez.movehabits.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val authRepository: AuthRepository) {

    suspend fun execute(email: String, password: String, name: String): FirebaseUser? {
        return authRepository.signUp(email, password, name)
    }
}