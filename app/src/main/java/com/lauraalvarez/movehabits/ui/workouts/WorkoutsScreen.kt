package com.lauraalvarez.movehabits.ui.workouts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lauraalvarez.movehabits.ui.layout.MoveHabitsButton


@Composable
fun WorkoutsScreen() {

    val workoutsViewModel: WorkoutsViewModel = hiltViewModel()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Workouts(
            Modifier.align(Alignment.Center),
            workoutsViewModel
        )
    }

}

@Composable
fun Workouts(modifier: Modifier, workoutsViewModel: WorkoutsViewModel, onAddWorkout: () -> Unit = {}) {

    MoveHabitsButton(true) { } // go to

}