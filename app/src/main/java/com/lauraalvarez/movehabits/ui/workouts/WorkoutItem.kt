package com.lauraalvarez.movehabits.ui.workouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lauraalvarez.movehabits.R
import com.lauraalvarez.movehabits.data.enums.ExerciseType
import com.lauraalvarez.movehabits.data.model.Workout
import com.lauraalvarez.movehabits.utils.DateTimeUtils.formatFirebaseDate
import com.lauraalvarez.movehabits.utils.DateTimeUtils.formatFirebaseTime


@Composable
fun WorkoutItem(workout: Workout) {

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
        ),
        modifier = Modifier.size(width = 350.dp, height = 200.dp)
    ) {
        //icon
        Row {
            //icon
            if (workout.type == ExerciseType.STRENGTH.name) {
                Icon(
                    painter = painterResource(id = R.drawable.workout_icon),
                    contentDescription = stringResource(R.string.strength_training_icon),
                    modifier = Modifier.size(50.dp)
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.profile_icon),
                    contentDescription = stringResource(R.string.cardio_training_icon),
                    modifier = Modifier.size(50.dp)
                )
            }
            Column {
                //fecha
                val date = formatFirebaseDate(workout.dateTime)
                Text(text = date)
                //hora
                val time = formatFirebaseTime(workout.dateTime)
                Text(text = time)
            }

            //icon goto or done
            if (workout.completed) {
                //Icon( painter = painterResource(id = R.drawable.home_icon), contentDescription = stringResource(R.string.done_icon), modifier = Modifier.size(50.dp))
            }else {
                //Icon( painter = painterResource(id = R.drawable.home_icon), contentDescription = stringResource(R.string.go_to_icon), modifier = Modifier.size(50.dp))
            }


        }
    }
}

