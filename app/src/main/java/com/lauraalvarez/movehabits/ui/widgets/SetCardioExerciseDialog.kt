package com.lauraalvarez.movehabits.ui.widgets


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.lauraalvarez.movehabits.R


@Composable
fun SetCardioExerciseDialog(
    exerciseName: String,
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit
) {
    var textInput by remember { mutableStateOf("1") }
    var mins by remember { mutableStateOf(1) }
    var isEnabled by remember { mutableStateOf(true) }

    val weightText = stringResource(R.string.time_mins_text)

    Dialog(onDismissRequest = onDismiss) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            shape = RoundedCornerShape(26.dp),
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 140.dp)
                .fillMaxWidth()
                .border(
                    1.dp,
                    color = colorResource(id = R.color.original_blue),
                    RoundedCornerShape(26.dp)
                ),
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                        .background(color = colorResource(id = R.color.original_blue))
                ) {
                    Text(
                        text = exerciseName,
                        modifier = Modifier.align(Alignment.Center),
                        color = colorResource(id = R.color.white),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = weightText,
                            modifier = Modifier.weight(0.3f),
                            fontSize = 14.sp
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 12.dp)
                            ) {
                                TextField(
                                    value = textInput,
                                    onValueChange = { newValue ->
                                        textInput = newValue

                                        if (newValue.isEmpty()) {
                                            isEnabled = false
                                        } else if (newValue.matches(Regex("^[0-9]+$"))) {
                                            mins = newValue.toInt()
                                            isEnabled = mins > 0
                                        }
                                    },
                                    keyboardOptions = KeyboardOptions.Default.copy(
                                        keyboardType = KeyboardType.Number
                                    ),
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .padding(end = 20.dp)
                                        .width(100.dp),
                                    textStyle = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Medium,
                                        textAlign = TextAlign.Center
                                    ),
                                    placeholder = {
                                        Text(
                                            text = "0",
                                            style = TextStyle(
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Medium,
                                                textAlign = TextAlign.Center
                                            ),
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    }
                                )
                            }


                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = onDismiss, colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(id = R.color.original_blue)
                            )
                        ) {
                            Text(text = stringResource(R.string.cancel_text))
                        }

                        Button(
                            onClick = { onConfirm(mins) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(id = R.color.original_blue)
                            ),
                            enabled = isEnabled
                        ) {
                            Text(text = stringResource(R.string.confirm_text))
                        }
                    }
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewSetcardioExerciseDialog() {
    SetCardioExerciseDialog(
        exerciseName = "Remo",
        onDismiss = {},
        onConfirm = { mins ->
            println("Mins: $mins")
        }
    )
}

