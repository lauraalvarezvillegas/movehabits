package com.lauraalvarez.movehabits.ui.signup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lauraalvarez.movehabits.R
import com.lauraalvarez.movehabits.ui.login.EmailField
import com.lauraalvarez.movehabits.ui.login.PasswordField
import kotlinx.coroutines.launch


@Composable
fun SignUpScreen(
    onNavigateToHome: () -> Unit
) {
    val signUpViewModel: SignUpViewModel = hiltViewModel()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        SignUp(
            Modifier.align(Alignment.Center),
            signUpViewModel,
            onNavigateToHome
        )
    }

}

@Composable
fun SignUp(modifier: Modifier, signUpViewModel: SignUpViewModel, onNavigateToHome: () -> Unit = {}) {
    val name: String by signUpViewModel.name.observeAsState(initial = "")
    val email: String by signUpViewModel.email.observeAsState(initial = "")
    val password: String by signUpViewModel.password.observeAsState(initial = "")
    val confirmPassword: String by signUpViewModel.confirmPassword.observeAsState(initial = "")
    val signUpEnabled: Boolean by signUpViewModel.signUpEnabled.observeAsState(false)
    val isLoading: Boolean by signUpViewModel.isLoading.observeAsState(false)
    val signupSuccess: Boolean by signUpViewModel.signUpSuccess.observeAsState(false)
    val errorMessageResId: Int? by signUpViewModel.errorKey.observeAsState(null)

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(signupSuccess) {
        if (signupSuccess) {
            onNavigateToHome()
        }
    }

    if (isLoading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        Column(
            modifier = modifier
        ) {
            NameField(name) {
                signUpViewModel.onSignUpChanged(name = it)
            }
            Spacer(modifier = Modifier.padding(16.dp))

            EmailField(email) {
                signUpViewModel.onSignUpChanged(email = it)
            }
            Spacer(modifier = Modifier.padding(16.dp))

            PasswordField(password) {
                signUpViewModel.onSignUpChanged(password = it)
            }
            Spacer(modifier = Modifier.padding(16.dp))

            ConfirmPassword(confirmPassword) {
                signUpViewModel.onSignUpChanged(confirmPassword = it)
            }
            Spacer(modifier = Modifier.padding(16.dp))

            SignUpButton(signUpEnabled) {
                coroutineScope.launch {
                    signUpViewModel.onRegisterSelected()
                }
            }

            errorMessageResId?.let { errorResId ->
                Spacer(modifier = Modifier.padding(top = 16.dp))
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = errorResId),
                        color = Color.Red,
                        style = LocalTextStyle.current,
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

        }
    }
}


@Composable
fun SignUpButton(isEnabled: Boolean, onSignUpButtonClicked: () -> Unit) {
    Button(
        onClick = { onSignUpButtonClicked() }, modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isEnabled) colorResource(R.color.original_blue) else Color.Gray
        )
    )
    {
        Text(text = stringResource(R.string.sing_up_text), color = Color.White)
    }
}

@Composable
fun ConfirmPassword(confirmPassword: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = confirmPassword,
        onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = stringResource(R.string.confirm_password_text),
                color = colorResource(id = R.color.original_blue)
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true,
        maxLines = 1,
        textStyle = LocalTextStyle.current.copy(
            color = colorResource(id = R.color.original_blue)
        )

    )
}


@Composable
fun NameField(name: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = name,
        onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = stringResource(R.string.name_text),
                color = colorResource(id = R.color.original_blue)
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        singleLine = true,
        maxLines = 1,
        textStyle = LocalTextStyle.current.copy(
            color = colorResource(id = R.color.original_blue)
        )

    )

}
