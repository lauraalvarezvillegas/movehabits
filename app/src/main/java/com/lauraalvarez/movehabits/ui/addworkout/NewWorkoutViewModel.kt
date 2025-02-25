package com.lauraalvarez.movehabits.ui.addworkout

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lauraalvarez.movehabits.data.model.Workout
import com.lauraalvarez.movehabits.data.model.WorkoutExercise
import com.lauraalvarez.movehabits.data.preferences.UserPreferences
import com.lauraalvarez.movehabits.domain.usecase.AddWorkoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.joda.time.LocalDate
import org.joda.time.LocalTime
import javax.inject.Inject


@HiltViewModel
class NewWorkoutViewModel @Inject constructor(
    val userPreferences: UserPreferences,
    val addWorkoutUseCase: AddWorkoutUseCase
) : ViewModel() {

    private val _userId = MutableLiveData<String?>()
    val userId: LiveData<String?> = _userId

    private val _selectedDate = MutableStateFlow<LocalDate?>(null)
    val selectedDate: StateFlow<LocalDate?> = _selectedDate

    private val _selectedTime = MutableStateFlow<LocalTime?>(null)
    val selectedTime: StateFlow<LocalTime?> = _selectedTime

    private val _exercises = MutableStateFlow<List<WorkoutExercise>>(emptyList())
    val exercises: StateFlow<List<WorkoutExercise>> = _exercises

    private val _newExerciseWorkout = MutableStateFlow<WorkoutExercise?>(null)
    val newExerciseWorkout: StateFlow<WorkoutExercise?> = _newExerciseWorkout

    private val _fromAddExercise = MutableStateFlow(false)
    val fromAddExercise: StateFlow<Boolean> = _fromAddExercise

    init {
        viewModelScope.launch {

            _userId.value = userPreferences.userId.firstOrNull()

            userPreferences.newExerciseWorkout.collect { exercise ->
                _newExerciseWorkout.value = exercise
            }

            userPreferences.fromAddEercise.collect { value -> _fromAddExercise.value = value }
        }
    }

    fun deleteExercise(exercise: WorkoutExercise) {
        _exercises.value = _exercises.value.filter { it != exercise }
    }

    fun setSelectedDate(date: LocalDate?) {
        _selectedDate.value = date
        if (date == null) {
            _selectedTime.value = null
        }
    }

    fun setSelectedTime(time: LocalTime?) {
        _selectedTime.value = time
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

    fun addWorkout(workout: Workout) {
        viewModelScope.launch {
            addWorkoutUseCase.execute(workout)
        }
    }

}
