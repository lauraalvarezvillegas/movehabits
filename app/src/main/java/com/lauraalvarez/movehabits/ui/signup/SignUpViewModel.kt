package com.lauraalvarez.movehabits.ui.signup

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class SignUpViewModel : ViewModel() {

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _surname = MutableLiveData<String>()
    val surname: LiveData<String> = _surname

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _confirmPassword = MutableLiveData<String>()
    val confirmPassword: LiveData<String> = _confirmPassword

    private val _signUpEnabled = MutableLiveData<Boolean>()
    val signUpEnabled: LiveData<Boolean> = _signUpEnabled

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun onSignUpChanged(
        name: String,
        surname: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        _email.value = email
        _password.value = password
        _signUpEnabled.value =
            isValidEmail(email) && isValidPassword(name, surname, password) && doPasswordsMatch(
                password,
                confirmPassword
            )
    }


    suspend fun onRegisterSelected() {
        _isLoading.value = true
        delay(4000)
        _isLoading.value = false
    }

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidPassword(name: String, surname: String, password: String): Boolean =
        password.length > 6 && name.isNotEmpty() && surname.isNotEmpty()

    private fun doPasswordsMatch(password: String, confirmPassword: String): Boolean =
        password == confirmPassword


}