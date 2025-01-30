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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.style.TextAlign
import com.lauraalvarez.movehabits.ui.widgets.ForgotPasswordDialog
import com.lauraalvarez.movehabits.ui.widgets.ResponseDialog
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    onNavigateToHome: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Login(
            Modifier.align(Alignment.Center),
            loginViewModel,
            onNavigateToHome,
            onNavigateToRegister
        )
    }

}

@Composable
fun Login(
    modifier: Modifier, loginViewModel: LoginViewModel, onNavigateToHome: () -> Unit = {},
    onNavigateToRegister: () -> Unit = {}
) {
    val email: String by loginViewModel.email.observeAsState(initial = "")
    val password: String by loginViewModel.password.observeAsState(initial = "")
    val loginEnabled: Boolean by loginViewModel.loginEnabled.observeAsState(false)
    val isLoading: Boolean by loginViewModel.isLoading.observeAsState(false)
    val loginSuccess: Boolean by loginViewModel.loginSuccess.observeAsState(false)
    val errorMessageResId: Int? by loginViewModel.errorKey.observeAsState(null)
    var showDialog by remember { mutableStateOf(false) }
    var showResponseDialog by remember { mutableStateOf(false) }
    var responseMessage by remember { mutableStateOf("") }


    val coroutineScope = rememberCoroutineScope()

    // if login is successful, navigate to home
    LaunchedEffect(loginSuccess) {
        if (loginSuccess) {
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
            HeaderImage(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(16.dp))
            EmailField(email) { loginViewModel.onLoginChanged(it, password) }
            Spacer(modifier = Modifier.padding(16.dp))
            PasswordField(password) { loginViewModel.onLoginChanged(email, it) }
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
            Spacer(modifier = Modifier.padding(16.dp))
            LoginButton(loginEnabled) {
                coroutineScope.launch {
                    loginViewModel.onLoginSelected()
                }
            }
            Spacer(modifier = Modifier.padding(10.dp))
            RegisterButton(onNavigateToRegister)
            Spacer(modifier = Modifier.padding(16.dp))
            ForgotPassword(Modifier.align(Alignment.CenterHorizontally)) {
                showDialog = true
            }

        }
    }

    if (showDialog) {
        ForgotPasswordDialog(
            onDismiss = { showDialog = false },
            onConfirm = { email ->
                coroutineScope.launch {
                    loginViewModel.onForgotPasswordSelected(email)
                    showResponseDialog = true
                    showDialog = false
                }
            }
        )
    }

    if (showResponseDialog) {
        ResponseDialog(
            message = stringResource(R.string.email_sent_text),
            onDismiss = { showResponseDialog = false }
        )
    }


}

@Composable
fun ForgotPassword(modifier: Modifier, onForgotPasswordClick: () -> Unit = {}) {
    Text(
        text = stringResource(R.string.forgot_password_text),
        modifier = modifier.clickable { onForgotPasswordClick() },
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
fun RegisterButton(onNavigateToRegister: () -> Unit) {
    Button(
        onClick = { onNavigateToRegister() }, modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.original_blue)
        )
    )
    {
        Text(text = stringResource(R.string.sing_up_here_text), color = Color.White)
    }

}






