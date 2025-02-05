package com.lauraalvarez.movehabits.ui.layout

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lauraalvarez.movehabits.R

@Composable
fun MoveHabitsButton(modifier: Modifier, text: String, isEnabled: Boolean, onButtonClicked: () -> Unit) {
    Button(
        onClick = { onButtonClicked() }, modifier = modifier,
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isEnabled) colorResource(R.color.original_blue) else Color.Gray
        )
    )
    {
        Text(text = text, color = Color.White)
    }
}