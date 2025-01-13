package com.lauraalvarez.movehabits.ui.signup

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(// private val signUpUseCase
) : ViewModel() {

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
        name: String? = null,
        surname: String? = null,
        email: String? = null,
        password: String? = null,
        confirmPassword: String? = null
    ) {
        _name.value = name ?: _name.value.orEmpty()
        _surname.value = surname ?: _surname.value.orEmpty()
        _email.value = email ?: _email.value.orEmpty()
        _password.value = password ?: _password.value.orEmpty()
        _confirmPassword.value = confirmPassword ?: _confirmPassword.value.orEmpty()

        _signUpEnabled.value = isValidEmail(_email.value.orEmpty()) &&
                isValidPassword(_name.value.orEmpty(), _surname.value.orEmpty(), _password.value.orEmpty()) &&
                doPasswordsMatch(_password.value.orEmpty(), _confirmPassword.value.orEmpty())
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