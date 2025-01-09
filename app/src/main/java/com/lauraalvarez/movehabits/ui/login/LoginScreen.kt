package com.lauraalvarez.movehabits.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lauraalvarez.movehabits.R
import androidx.compose.ui.text.input.PasswordVisualTransformation
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(loginViewModel: LoginViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Login(Modifier.align(Alignment.Center), loginViewModel)
    }

}

@Composable
fun Login(modifier: Modifier, loginViewModel: LoginViewModel) {
    val email: String by loginViewModel.email.observeAsState(initial = "")
    val password: String by loginViewModel.password.observeAsState(initial = "")
    val loginEnabled: Boolean by loginViewModel.loginEnabled.observeAsState(false)
    val isLoading: Boolean by loginViewModel.isLoading.observeAsState(false)

    val coroutineScope = rememberCoroutineScope()

    if (isLoading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        Column(
            modifier = modifier
        ) {
            HeaderImage(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(16.dp))
            EmailField(email) { loginViewModel.onLoginChanged(it, password) }
            Spacer(modifier = Modifier.padding(16.dp))
            PasswordField(password) { loginViewModel.onLoginChanged(email, it) }
            Spacer(modifier = Modifier.padding(16.dp))
            ForgotPassword(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(16.dp))
            LoginButton(loginEnabled) {
                coroutineScope.launch {
                    loginViewModel.onLoginSelected()
                }
            }
            Spacer(modifier = Modifier.padding(10.dp))
            RegisterButton()
        }
    }

}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = stringResource(R.string.forgot_password_text),
        modifier = modifier.clickable { },
        color = colorResource(R.color.original_blue),
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun EmailField(email: String, onTextFieldChange: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextFieldChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = stringResource(R.string.login_email_text),
                color = colorResource(id = R.color.original_blue)
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        textStyle = LocalTextStyle.current.copy(
            color = colorResource(id = R.color.original_blue)
        )
    )
}

@Composable
fun PasswordField(password: String, onTextFieldChange: (String) -> Unit) {
    TextField(
        value = password,
        onValueChange = { onTextFieldChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = stringResource(R.string.login_password_text),
                color = colorResource(id = R.color.original_blue)
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(
            color = colorResource(id = R.color.original_blue)
        )
    )
}

@Composable
fun HeaderImage(modifier: Modifier) {
    Image(
        modifier = modifier.size(170.dp),
        painter = painterResource(id = R.drawable.logo_movehabit),
        contentDescription = "app icon"
    )
}

@Composable
fun LoginButton(isEnabled: Boolean, onLoginSelected: () -> Unit) {
    Button(
        onClick = { onLoginSelected() }, modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isEnabled) colorResource(R.color.original_blue) else Color.Gray
        )
    )
    {
        Text(text = stringResource(R.string.login_in_text), color = Color.White)
    }

}

@Composable
fun RegisterButton() {
    Button(
        onClick = { }, modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.original_blue)
        )
    )
    {
        Text(text = stringResource(R.string.sing_up_text), color = Color.White)
    }

}






