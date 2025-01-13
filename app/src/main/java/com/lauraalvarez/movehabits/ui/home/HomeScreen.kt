package com.lauraalvarez.movehabits.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.lauraalvarez.movehabits.R

@Composable
fun HomeScreen(){
    Column {
        TextField(
            value = "name",
            onValueChange = { },
            modifier = Modifier.fillMaxSize(),
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
}