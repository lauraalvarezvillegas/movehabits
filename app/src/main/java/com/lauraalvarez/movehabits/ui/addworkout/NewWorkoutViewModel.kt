package com.lauraalvarez.movehabits.ui.addworkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lauraalvarez.movehabits.data.model.WorkoutExercise
import com.lauraalvarez.movehabits.data.preferences.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewWorkoutViewModel @Inject constructor(
    val userPreferences: UserPreferences
) : ViewModel() {

    private val _exercises = MutableStateFlow<List<WorkoutExercise>>(emptyList())
    val exercises: StateFlow<List<WorkoutExercise>> = _exercises

    private val _newExerciseWorkout = MutableStateFlow<WorkoutExercise?>(null)
    val newExerciseWorkout: StateFlow<WorkoutExercise?> = _newExerciseWorkout

    private val _fromAddExercise = MutableStateFlow(false)
    val fromAddExercise: StateFlow<Boolean> = _fromAddExercise

    init {
        viewModelScope.launch {
            userPreferences.newExerciseWorkout.collect { exercise ->
                _newExerciseWorkout.value = exercise
            }

            userPreferences.fromAddEercise.collect { value -> _fromAddExercise.value = value }
        }
    }

    fun addExercise(exercise: WorkoutExercise) {
        _exercises.value += exercise
    }

    fun setFromAddExercise(value: Boolean) {
        viewModelScope.launch {
            userPreferences.saveFromAddExercise(value)
        }

    }

    fun getFromAddExercise() {
        viewModelScope.launch {
            _fromAddExercise.value = userPreferences.getFromAddExercise()
        }
    }

    fun clearNewExerciseWorkout() {
        viewModelScope.launch {
            userPreferences.clearNewExerciseWorkout()
            _newExerciseWorkout.value = null
        }
    }
}
