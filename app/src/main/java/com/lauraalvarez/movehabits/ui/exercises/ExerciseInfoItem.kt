package com.lauraalvarez.movehabits.ui.exercises

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lauraalvarez.movehabits.R
import com.lauraalvarez.movehabits.data.enums.ExerciseClassification
import com.lauraalvarez.movehabits.data.enums.ExerciseType
import com.lauraalvarez.movehabits.data.model.Exercise
import coil.compose.AsyncImage

@Composable
fun ExerciseInfoItem(exercise: Exercise) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(26.dp),
        modifier = Modifier
            .size(width = 350.dp, height = 120.dp)
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
            AsyncImage(
                model = exercise.img,
                contentDescription = stringResource(R.string.img_icon),
                modifier = Modifier.size(90.dp),
                contentScale = ContentScale.Crop
            )


            Text(
                text = exercise.exercisename, fontSize = 16.sp, modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            )


            Icon(
                painter = painterResource(
                    R.drawable.add_icon
                ),
                contentDescription = stringResource(
                    R.string.add_exercise_icon
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
fun PreviewExerciseInfoItem() {
    ExerciseInfoItem(
        exercise = Exercise(
            exerciseId = 1,
            exercisename = "Sentadilla",
            type = ExerciseType.STRENGTH,
            classification = ExerciseClassification.LOWER_BODY,
            img = "https://firebasestorage.googleapis.com/v0/b/movehabit-lav.appspot.com/o/Tren%20inferior%2FSentadillabarra.png?alt=media&token=b66f208b-981f-41f4-afb4-1023975156e1"

        )
    )
}

