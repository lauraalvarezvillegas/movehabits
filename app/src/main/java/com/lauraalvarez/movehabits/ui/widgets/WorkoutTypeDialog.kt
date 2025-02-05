package com.lauraalvarez.movehabits.ui.widgets

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.lauraalvarez.movehabits.R
import com.lauraalvarez.movehabits.data.enums.ExerciseType

@Composable
fun WorkoutTypeDialog(
    onDismiss: () -> Unit,
    onWorkoutSelected: (ExerciseType) -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconButton(onClick = onDismiss) {
                        Icon(
                            painterResource(id = R.drawable.close_icon),
                            contentDescription = stringResource(R.string.close_text)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.select_type),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    WorkoutTypeButton(
                        iconRes = R.drawable.strength_icon,
                        text = stringResource(R.string.exercise_type_strength),
                        onClick = { onWorkoutSelected(ExerciseType.STRENGTH) },
                        modifier = Modifier.weight(1f)

                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    WorkoutTypeButton(
                        iconRes = R.drawable.running_icon,
                        text = stringResource(R.string.exercise_type_cardio_short),
                        onClick = { onWorkoutSelected(ExerciseType.CARDIO) },
                        modifier = Modifier.weight(1f)

                    )
                }


                Spacer(modifier = Modifier.height(26.dp))
            }
        }
    }
}


@Composable
fun WorkoutTypeButton(
    iconRes: Int,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.original_blue)),
        modifier = modifier
            .height(100.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = text,
                modifier = Modifier.size(48.dp),
                tint = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = text, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}



