package com.lauraalvarez.movehabits.ui.workouts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import com.lauraalvarez.movehabits.data.model.Workout
import com.lauraalvarez.movehabits.ui.layout.MoveHabitsButton


@Composable
fun WorkoutsScreen() {

    val workoutsViewModel: WorkoutsViewModel = hiltViewModel()
    val workouts by workoutsViewModel.workouts.collectAsState()

    LaunchedEffect(Unit) {
        workoutsViewModel.loadWorkouts()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Workouts(
            Modifier.align(Alignment.Center),
            workoutsViewModel,
            workouts
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

    MoveHabitsButton(true) { } // go to add workout
    //list of workouts
    LazyRow{
        items(workouts.size) { workout ->
            WorkoutItem(workouts[workout])
        }
    }




}