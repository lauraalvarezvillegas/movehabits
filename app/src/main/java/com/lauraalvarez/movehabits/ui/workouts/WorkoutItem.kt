package com.lauraalvarez.movehabits.ui.workouts

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Timestamp
import com.lauraalvarez.movehabits.R
import com.lauraalvarez.movehabits.data.enums.ExerciseType
import com.lauraalvarez.movehabits.data.model.Workout
import com.lauraalvarez.movehabits.utils.DateTimeUtils.formatFirebaseDate
import com.lauraalvarez.movehabits.utils.DateTimeUtils.formatFirebaseTime
import java.util.Date
@Composable
fun WorkoutItem(workout: Workout) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(26.dp),
        modifier = Modifier
            .size(width = 350.dp, height = 100.dp)
            .border(
                1.dp,
                color = colorResource(id = R.color.original_blue),
                RoundedCornerShape(26.dp)
            ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White, shape = RoundedCornerShape(26.dp))
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(
                    id = if (workout.type.name == ExerciseType.STRENGTH.name)
                        R.drawable.strength_icon
                    else
                        R.drawable.running_icon
                ),
                contentDescription = stringResource(
                    if (workout.type.name == ExerciseType.STRENGTH.name)
                        R.string.strength_training_icon
                    else
                        R.string.cardio_training_icon
                ),
                modifier = Modifier
                    .size(70.dp)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = formatFirebaseDate(workout.dateTime), fontSize = 16.sp)
                Text(text = formatFirebaseTime(workout.dateTime), fontSize = 16.sp)
            }

            Icon(
                painter = painterResource(
                    id = if (workout.completed)
                        R.drawable.check_icon
                    else
                        R.drawable.right_icon
                ),
                contentDescription = stringResource(
                    if (workout.completed)
                        R.string.finished_workout_icon
                    else
                        R.string.strength_training_icon
                ),
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun WorkoutItemPreview() {
    val sampleWorkout = Workout(
        userId = "123",
        type = ExerciseType.CARDIO,
        dateTime = Timestamp(Date()),
        totalDurationSec = 3600,
        restSec = 60,
        completed = false,
        exercises = emptyList()
    )

    WorkoutItem(workout = sampleWorkout)
}
