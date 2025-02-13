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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.lauraalvarez.movehabits.R


@Composable
fun SetExerciseDialog(
    exerciseName: String,
    onDismiss: () -> Unit,
    onConfirm: (Int, Int, Int) -> Unit
) {
    var sets by remember { mutableStateOf(1) }
    var reps by remember { mutableStateOf(1) }
    var weight by remember { mutableStateOf(0) }

    val setsText = stringResource(R.string.sets_text)
    val repsText = stringResource(R.string.reps_text)
    val weightText = stringResource(R.string.weight_text)

    Dialog(onDismissRequest = onDismiss) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            shape = RoundedCornerShape(26.dp),
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 140.dp)
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
                    listOf(
                        Triple(setsText, sets) { newValue: Int -> sets = newValue },
                        Triple(repsText, reps) { newValue: Int -> reps = newValue },
                        Triple(weightText, weight) { newValue: Int -> weight = newValue }
                    ).forEach { (label, value, setter) ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = label,
                                modifier = Modifier.weight(1f),
                                fontSize = 15.sp
                            )

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Button(
                                    onClick = { setter((value - 1).coerceAtLeast(if (label == weightText) 0 else 1)) },
                                    enabled = !(label != weightText && value == 1),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = colorResource(id = R.color.original_blue),
                                        disabledContainerColor = Color.Gray.copy(alpha = 0.4f)
                                    ),
                                    shape = CircleShape,
                                    modifier = Modifier.size(55.dp)
                                ) {
                                    Text(
                                        text = "-",
                                        fontSize = 28.sp,
                                        fontWeight = FontWeight.Light
                                    )
                                }

                                Text(
                                    text = value.toString(),
                                    modifier = Modifier.padding(horizontal = 12.dp),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Medium
                                )

                                Button(
                                    onClick = { setter(value + 1) },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = colorResource(id = R.color.original_blue)
                                    ),
                                    shape = CircleShape,
                                    modifier = Modifier.size(55.dp)
                                ) {
                                    Text(
                                        text = "+",
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Medium,
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))
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
                        onClick = { onConfirm(sets, reps, weight) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.original_blue)
                        )
                    ) {
                        Text(text = stringResource(R.string.confirm_text))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSetExerciseDialog() {
    SetExerciseDialog(
        exerciseName = "Curl de bíceps",
        onDismiss = {},
        onConfirm = { sets, reps, weight ->
            println("Sets: $sets, Reps: $reps, Peso: $weight kg")
        }
    )
}


