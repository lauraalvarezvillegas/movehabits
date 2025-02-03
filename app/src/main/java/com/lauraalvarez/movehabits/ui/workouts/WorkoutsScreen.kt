package com.lauraalvarez.movehabits.ui.workouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lauraalvarez.movehabits.data.enums.ExerciseClassification
import com.lauraalvarez.movehabits.data.enums.ExerciseType
import com.lauraalvarez.movehabits.data.model.Exercise
import com.lauraalvarez.movehabits.data.model.Workout
import com.lauraalvarez.movehabits.ui.layout.MoveHabitsButton


@Composable
fun WorkoutsScreen() {

    val workoutsViewModel: WorkoutsViewModel = hiltViewModel()
    val workouts by workoutsViewModel.workouts.collectAsState()
    val userId by workoutsViewModel.userId.collectAsState()

    LaunchedEffect(userId) {
        userId?.let {
            workoutsViewModel.loadWorkouts(it)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Workouts Column with top-aligned button and centered list
        Workouts(
            modifier = Modifier.align(Alignment.TopCenter),
            workoutsViewModel = workoutsViewModel,
            workouts = workouts
        )
    }
}


@Composable
fun Workouts(
    modifier: Modifier,
    workoutsViewModel: WorkoutsViewModel,
    workouts: List<Workout>,
    onAddWorkout: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        MoveHabitsButton(isEnabled = true) {
            val newWorkout = Workout(
                userId = "user1",
                type = ExerciseType.STRENGTH,
                totalDurationSec = 300,
                restSec = 60,
                completed = false,
                exercises = listOf(
                    Exercise(
                        exerciseId = 1,
                        exercisename = "Push Up",
                        type = ExerciseType.STRENGTH,
                        classification = ExerciseClassification.UPPER_BODY,
                        img = "url_de_imagen"
                    )
                )
            )
            workoutsViewModel.addWorkout(newWorkout)
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center // Center the items in LazyRow
        ) {
            items(workouts.size) { index ->
                WorkoutItem(workouts[index])
            }
        }

    }
}


