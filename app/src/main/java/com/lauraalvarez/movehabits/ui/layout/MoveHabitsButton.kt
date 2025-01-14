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
fun MoveHabitsButton(isEnabled: Boolean, onSignUpButtonClicked: () -> Unit) {
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
        Text(text = stringResource(R.string.new_workout_text), color = Color.White)
    }
}