package com.lauraalvarez.movehabits.ui.exercises

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lauraalvarez.movehabits.R
import com.lauraalvarez.movehabits.data.enums.ExerciseClassification
import com.lauraalvarez.movehabits.data.enums.ExerciseType
import com.lauraalvarez.movehabits.data.model.Exercise
import com.lauraalvarez.movehabits.data.model.WorkoutExercise
import com.lauraalvarez.movehabits.ui.addworkout.NewWorkoutViewModel
import com.lauraalvarez.movehabits.ui.navigation.NewWorkout
import com.lauraalvarez.movehabits.ui.widgets.SetCardioExerciseDialog
import com.lauraalvarez.movehabits.ui.widgets.SetExerciseDialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ExercisesScreen(type: ExerciseType, navController: NavController) {
    val exercisesViewModel: ExercisesViewModel = hiltViewModel()
    val workoutViewModel: NewWorkoutViewModel = hiltViewModel()
    val exercises by exercisesViewModel.exercises.collectAsState()
    var selectedExercise by remember { mutableStateOf<Exercise?>(null) }

    val typeName = type.getDisplayName(LocalContext.current)
    var showDialog by remember { mutableStateOf(false) }

    var isLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(type.getDisplayName(LocalContext.current)) {
        exercisesViewModel.getExercises(typeName)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Exercises(
            type = type,
            modifier = Modifier.align(Alignment.TopCenter),
            exercises = exercises,
            onAddExercise = {
                selectedExercise = it
                showDialog = true
            }
        )
    }

    val coroutineScope = rememberCoroutineScope()

    if (showDialog) {
        selectedExercise?.let {
            if (selectedExercise!!.type == ExerciseType.STRENGTH.getDisplayName(context)) {
                SetExerciseDialog(
                    it.exercisename,
                    onDismiss = { showDialog = false },
                    onConfirm = { sets, reps, weight ->
                        // Muestra el loading
                        isLoading = true

                        val workoutExercise = WorkoutExercise(
                            "",
                            it.exercisename,
                            sets,
                            reps,
                            weight,
                            0,
                            0,
                            false
                        )
                        exercisesViewModel.storeNewWorkoutExercise(workoutExercise)

                        // launch the coroutine to wait for isFromAddExercise to be true
                        coroutineScope.launch {
                            workoutViewModel.setFromAddExercise(true)
                            showDialog = false

                            // waits until isFromAddExercise its true
                            while (true) {
                                val isFromAddExercise =
                                    workoutViewModel.userPreferences.getFromAddExercise()
                                if (isFromAddExercise) {
                                    break
                                }
                                delay(100)
                            }

                            isLoading = false
                            navController.popBackStack()
                        }
                    }
                )
            } else {
                SetCardioExerciseDialog(it.exercisename,
                    onDismiss = { showDialog = false },
                    onConfirm = { mins ->
                        isLoading = true

                        val workoutExercise = WorkoutExercise(
                            "",
                            it.exercisename,
                            mins,
                            0,
                            0f,
                            0,
                            0,
                            false
                        )
                        exercisesViewModel.storeNewWorkoutExercise(workoutExercise)

                        // launch the coroutine to wait for isFromAddExercise to be true
                        coroutineScope.launch {
                            workoutViewModel.setFromAddExercise(true)
                            showDialog = false

                            // waits until isFromAddExercise its true
                            while (true) {
                                val isFromAddExercise =
                                    workoutViewModel.userPreferences.getFromAddExercise()
                                if (isFromAddExercise) {
                                    break
                                }
                                delay(100)
                            }

                            isLoading = false
                            navController.popBackStack()
                        }

                    }
                )
            }

        }
    }

    if (isLoading) {
        LoadingIndicator()
    }
}


@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun Exercises(
    type: ExerciseType,
    modifier: Modifier,
    exercises: List<Exercise>,
    onAddExercise: (Exercise) -> Unit
) {
    var selectedClassification by remember {
        mutableStateOf<ExerciseClassification?>(
            ExerciseClassification.NONE
        )
    }
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            if (type == ExerciseType.STRENGTH) {
                Button(
                    onClick = { expanded = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.original_blue))
                ) {
                    Text(
                        text = selectedClassification?.getDisplayName(context)
                            ?: stringResource(R.string.classification_none)
                    )

                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 20.dp)
                        .background(Color.White, shape = RoundedCornerShape(12.dp))
                ) {
                    ExerciseClassification.entries.forEach { classification ->
                        if (classification != ExerciseClassification.NONE) {
                            DropdownMenuItem(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp),
                                text = {
                                    Text(
                                        text = classification.getDisplayName(context),
                                        color = Color.Black
                                    )
                                },
                                onClick = {
                                    selectedClassification = classification
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }

        var filteredExercises = exercises

        if (selectedClassification?.equals(ExerciseClassification.NONE) == false) { // if type == upper or type == lower -> filter exercises
            filteredExercises = selectedClassification?.let { classification ->
                exercises.filter { it.classification == classification.getDisplayName(LocalContext.current) }
            } ?: exercises

        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            items(filteredExercises.size) { index ->
                ExerciseInfoItem(exercise = filteredExercises[index], onAddExercise = onAddExercise)
            }
        }
    }
}


