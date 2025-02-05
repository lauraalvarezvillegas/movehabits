package com.lauraalvarez.movehabits.ui.workouts

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lauraalvarez.movehabits.R
import com.lauraalvarez.movehabits.data.model.WorkoutExercise


@Composable
fun ExerciseAtWorkoutItem(exercise: WorkoutExercise) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(26.dp),
        modifier = Modifier
            .size(width = 350.dp, height = 180.dp)
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
        Column(modifier = Modifier.padding(20.dp)) {
            Row(modifier = Modifier.padding(2.dp)) {

                Text(
                    text = "Series : ",
                )
                Text(
                    text = exercise.sets.toString(),
                )

            }

            Row(modifier = Modifier.padding(2.dp)) {

                Text(
                    text = "Repeticiones : ",
                )
                Text(
                    text = exercise.repetitions.toString(),
                )

            }

            Row(modifier = Modifier.padding(2.dp)) {

                Text(
                    text = "Peso : ",
                )
                Text(
                    text = exercise.weight.toString(),
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

