package com.lauraalvarez.movehabits.ui.widgets

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.lauraalvarez.movehabits.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerMaterialTheme(timePickerState: TimePickerState) {


    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            surface = colorResource(R.color.white),
            primary = colorResource(R.color.original_blue),
            onPrimary = colorResource(R.color.black)
        )
    ) {
        Surface(
            tonalElevation = 6.dp,
            shape = MaterialTheme.shapes.extraLarge
        ) {
            TimePicker(
                state = timePickerState
            )
        }
    }
}