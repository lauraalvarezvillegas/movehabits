package com.lauraalvarez.movehabits.ui.workouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lauraalvarez.movehabits.R
import com.lauraalvarez.movehabits.data.model.Workout
import com.lauraalvarez.movehabits.ui.layout.MoveHabitsButton
import com.lauraalvarez.movehabits.ui.widgets.WorkoutTypeDialog


@Composable
fun WorkoutsScreen() {
    val workoutsViewModel: WorkoutsViewModel = hiltViewModel()
    val workouts by workoutsViewModel.workouts.collectAsState()
    val userId by workoutsViewModel.userId.collectAsState()

    var showDialog by remember { mutableStateOf(false) }

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
        Workouts(
            modifier = Modifier.align(Alignment.TopCenter),
            workoutsViewModel = workoutsViewModel,
            workouts = workouts,
            onAddWorkout = { showDialog = true }
        )
    }

    if (showDialog) {
        WorkoutTypeDialog(
            onDismiss = { showDialog = false },
            onWorkoutSelected = { selectedWorkoutType ->
                showDialog = false
            }
        )
    }

}

@Composable
fun Workouts(
    modifier: Modifier,
    workoutsViewModel: WorkoutsViewModel,
    workouts: List<Workout>,
    onAddWorkout: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        MoveHabitsButton(
            Modifier
                .fillMaxWidth()
                .height(50.dp), stringResource(R.string.login_in_text), true
        ) {
            onAddWorkout()
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            items(workouts.size) { index ->
                WorkoutItem(workouts[index])
            }
        }
    }
}



