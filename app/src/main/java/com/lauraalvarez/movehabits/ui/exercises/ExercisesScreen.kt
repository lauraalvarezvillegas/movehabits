package com.lauraalvarez.movehabits.ui.exercises

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lauraalvarez.movehabits.R
import com.lauraalvarez.movehabits.data.enums.ExerciseClassification
import com.lauraalvarez.movehabits.data.enums.ExerciseType
import com.lauraalvarez.movehabits.data.model.Exercise

@Composable
fun ExercisesScreen(type: ExerciseType) {
    val exercisesViewModel: ExercisesViewModel = hiltViewModel()
    val exercises by exercisesViewModel.exercises.collectAsState()
    var selectedExercise by remember { mutableStateOf<Exercise?>(null) }


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
                //TODO : Dialog de sets, repeticiones y peso
            }
        )

    }


}

@Composable
fun Exercises(
    type: ExerciseType,
    modifier: Modifier,
    exercises: List<Exercise>,
    onAddExercise: (Exercise) -> Unit
) {
    var selectedClassification by remember { mutableStateOf<ExerciseClassification?>(null) }
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
                            ?: stringResource(R.string.select_type2)
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
                                    .padding(horizontal = 20.dp), // Margen horizontal en los items
                                text = {
                                    Text(
                                        text = classification.getDisplayName(context),
                                        color = Color.Black // Asegurar que el texto sea visible sobre el fondo blanco
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

        // Lista de ejercicios filtrados
        val filteredExercises = selectedClassification?.let { classification ->
            exercises.filter { it.classification == classification }
        } ?: exercises

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


@Preview(showBackground = true)
@Composable
fun ExercisesScreenPreview() {
    val sampleExercises = listOf(
        Exercise(
            exerciseId = 1,
            exercisename = "Sentadilla",
            type = ExerciseType.STRENGTH,
            classification = ExerciseClassification.LOWER_BODY,
            img = "https://example.com/squat.png"
        ),
        Exercise(
            exerciseId = 2,
            exercisename = "Press de Banca",
            type = ExerciseType.STRENGTH,
            classification = ExerciseClassification.UPPER_BODY,
            img = "https://example.com/benchpress.png"
        ),
        Exercise(
            exerciseId = 3,
            exercisename = "Biceps",
            type = ExerciseType.STRENGTH,
            classification = ExerciseClassification.UPPER_BODY,
            img = "https://example.com/running.png"
        )
    )

    Exercises(
        type = ExerciseType.STRENGTH,
        modifier = Modifier.fillMaxSize(),
        exercises = sampleExercises,
        onAddExercise = {}
    )
}

