package com.lauraalvarez.movehabits.ui.workouts

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lauraalvarez.movehabits.R
import com.lauraalvarez.movehabits.data.model.WorkoutExercise


@Composable
fun ExerciseAtWorkoutItem(exercise: WorkoutExercise, onDeleteButtonClicked: () -> Unit = {}) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(26.dp),
        modifier = Modifier
            .width(350.dp)
            .border(
                1.dp,
                color = colorResource(id = R.color.original_blue),
                RoundedCornerShape(26.dp)
            ),
    ) {
        Box(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.original_blue))
        ) {
            Text(
                text = exercise.exerciseName,
                modifier = Modifier.align(Alignment.Center),
                color = colorResource(id = R.color.white)
            )
        }

        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.padding(end = 20.dp)) {
                Text(
                    text = stringResource(R.string.sets_text),
                    modifier = Modifier.padding(bottom = 5.dp)
                )
                Text(
                    text = stringResource(R.string.reps_text),
                    modifier = Modifier.padding(bottom = 5.dp)
                )
                Text(
                    text = stringResource(R.string.weight_text),
                    modifier = Modifier.padding(bottom = 5.dp)
                )
            }

            Column(modifier = Modifier.padding(end = 20.dp)) {
                Text(text = exercise.sets.toString(), modifier = Modifier.padding(bottom = 5.dp))
                Text(
                    text = exercise.repetitions.toString(),
                    modifier = Modifier.padding(bottom = 5.dp)
                )
                Text(
                    text = "${exercise.weight} ${stringResource(R.string.kg_text)}",
                    modifier = Modifier.padding(bottom = 5.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                modifier = Modifier
                    .padding(start = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.original_blue)
                ),
                onClick = { onDeleteButtonClicked() }
            ) {
                Icon(
                    painter = painterResource(R.drawable.bin_icon),
                    contentDescription = stringResource(R.string.delete_icon),
                    tint = colorResource(id = R.color.white),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewExerciseItem() {
    ExerciseAtWorkoutItem(WorkoutExercise("", "Curl de biceps", 3, 12, 5, 0, 0, false))
}

