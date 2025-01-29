package com.lauraalvarez.movehabits.ui.signup

import android.util.Patterns
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.lauraalvarez.movehabits.R
import com.lauraalvarez.movehabits.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

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

    private val _signUpSuccess = MutableLiveData<Boolean>()
    val signUpSuccess: LiveData<Boolean> = _signUpSuccess

    private val _errorKey = MutableLiveData<Int?>()
    val errorKey: LiveData<Int?> = _errorKey

    private val _registeredUser = MutableLiveData<FirebaseUser?>()
    val registeredUser: LiveData<FirebaseUser?> = _registeredUser


    fun onSignUpChanged(
        name: String? = null,
        email: String? = null,
        password: String? = null,
        confirmPassword: String? = null
    ) {
        _name.value = name ?: _name.value.orEmpty()
        _email.value = email ?: _email.value.orEmpty()
        _password.value = password ?: _password.value.orEmpty()
        _confirmPassword.value = confirmPassword ?: _confirmPassword.value.orEmpty()

        _signUpEnabled.value = isValidEmail(_email.value.orEmpty()) &&
                isValidPassword(_name.value.orEmpty(), _password.value.orEmpty()) &&
                doPasswordsMatch(_password.value.orEmpty(), _confirmPassword.value.orEmpty())
    }

    fun onRegisterSelected() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val user = signUpUseCase.execute(
                    name = _name.value.orEmpty(),
                    email = _email.value.orEmpty(),
                    password = _password.value.orEmpty()
                )
                if (user != null) {
                    _registeredUser.value = user
                    _signUpSuccess.value = true
                    _errorKey.value = null
                } else {
                    _signUpSuccess.value = false
                    _errorKey.value = R.string.sing_up_error_text
                }
            } catch (e: Exception) {
                _signUpSuccess.value = false
                _errorKey.value = R.string.sing_up_error_text
            } finally {
                _isLoading.value = false
            }
        }
    }



    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidPassword(name: String, password: String): Boolean =
        password.length > 6 && name.isNotEmpty()

    private fun doPasswordsMatch(password: String, confirmPassword: String): Boolean =
        password == confirmPassword
}
