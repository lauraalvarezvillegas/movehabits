package com.lauraalvarez.movehabits.ui.workouts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lauraalvarez.movehabits.data.model.Workout
import com.lauraalvarez.movehabits.data.preferences.UserPreferences
import com.lauraalvarez.movehabits.domain.usecase.AddWorkoutUseCase
import com.lauraalvarez.movehabits.domain.usecase.GetWorkoutsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutsViewModel @Inject constructor(
    private val getWorkoutsUseCase: GetWorkoutsUseCase,
    private val addWorkoutUseCase: AddWorkoutUseCase,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _workouts = MutableStateFlow<List<Workout>>(emptyList())
    val workouts: StateFlow<List<Workout>> = _workouts

    private val _userId = MutableStateFlow<String?>(null)
    val userId: StateFlow<String?> = _userId

    init {
        viewModelScope.launch {
            userPreferences.userId.collect { id ->
                _userId.value = id
            }
        }
    }

    fun loadWorkouts(userId: String?) {
        if (userId != null) {
            viewModelScope.launch {
                _workouts.value = getWorkoutsUseCase.execute(userId)
            }
        }
    }

    fun addWorkout(workout: Workout) {
        viewModelScope.launch {
            addWorkoutUseCase.execute(workout)
        }
    }
}

