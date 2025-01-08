package com.lauraalvarez.movehabits.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lauraalvarez.movehabits.R

@Composable
fun LoginScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Login(Modifier.align(Alignment.Center))
    }

}

@Composable
fun Login(modifier: Modifier) {
    Column(
        modifier = modifier
    ) {
        HeaderImage(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(16.dp))
        EmailField()
        Spacer(modifier = Modifier.padding(16.dp))
        PasswordField()
        Spacer(modifier = Modifier.padding(16.dp))
        ForgotPassword(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(16.dp))
        LoginButton(false)
        Spacer(modifier = Modifier.padding(10.dp))
        RegisterButton()


    }
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = stringResource(R.string.forgot_password),
        modifier = modifier.clickable { },
        color = colorResource(R.color.original_blue),
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun EmailField() {
    TextField(
        value = "", onValueChange = {},
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
fun PasswordField() {
    TextField(
        value = "", onValueChange = {},
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = stringResource(R.string.login_password_text),
                color = colorResource(id = R.color.original_blue)
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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
fun LoginButton(isEnabled: Boolean) {
    Button(
        onClick = {}, modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
        //.shadow(8.dp, shape = RoundedCornerShape(12.dp))
        ,
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isEnabled) colorResource(R.color.original_blue) else Color.Gray
        )
    )
    {
        Text(text = stringResource(R.string.login_in), color = Color.White)
    }

}

@Composable
fun RegisterButton() {
    Button(
        onClick = {}, modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
        //.shadow(8.dp, shape = RoundedCornerShape(12.dp))
        ,
        colors = ButtonDefaults.buttonColors(
            containerColor =  colorResource(R.color.original_blue)
        )
    )
    {
        Text(text = stringResource(R.string.login_in), color = Color.White)
    }

}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}





