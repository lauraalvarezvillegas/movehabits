package com.lauraalvarez.movehabits.ui.exercises

import androidx.lifecycle.ViewModel
import com.lauraalvarez.movehabits.data.model.Exercise
import com.lauraalvarez.movehabits.data.model.Workout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ExercisesViewModel @Inject constructor() : ViewModel() {

    private val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises: StateFlow<List<Exercise>> = _exercises


}