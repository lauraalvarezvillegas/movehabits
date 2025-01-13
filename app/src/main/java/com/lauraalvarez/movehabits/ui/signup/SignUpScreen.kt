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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lauraalvarez.movehabits.R
import com.lauraalvarez.movehabits.ui.login.EmailField
import com.lauraalvarez.movehabits.ui.login.PasswordField
import kotlinx.coroutines.launch


@Composable
fun SignUpScreen() {
    val signUpViewModel: SignUpViewModel = hiltViewModel()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        SignUp(
            Modifier.align(Alignment.Center),
            signUpViewModel
            //onNavigateTo
        )
    }

}

@Composable
fun SignUp(modifier: Modifier, signUpViewModel: SignUpViewModel) {
    val name: String by signUpViewModel.name.observeAsState(initial = "")
    val surname: String by signUpViewModel.surname.observeAsState(initial = "")
    val email: String by signUpViewModel.email.observeAsState(initial = "")
    val password: String by signUpViewModel.password.observeAsState(initial = "")
    val confirmPassword: String by signUpViewModel.confirmPassword.observeAsState(initial = "")
    val signUpEnabled: Boolean by signUpViewModel.signUpEnabled.observeAsState(false)
    val isLoading: Boolean by signUpViewModel.isLoading.observeAsState(false)

    val coroutineScope = rememberCoroutineScope()

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

            SurnameField(surname) {
                signUpViewModel.onSignUpChanged(surname = it)
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
fun SurnameField(surname: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = surname,
        onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = stringResource(R.string.surname_text),
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
