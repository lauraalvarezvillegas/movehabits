package com.lauraalvarez.movehabits.ui.exercises

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lauraalvarez.movehabits.data.model.Exercise
import com.lauraalvarez.movehabits.domain.usecase.GetExerciseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExercisesViewModel @Inject constructor(
    private val getExerciseUseCase: GetExerciseUseCase
) : ViewModel() {

    private val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises: StateFlow<List<Exercise>> = _exercises

    fun getExercises(type: String) {
        viewModelScope.launch {
            _exercises.value = getExerciseUseCase.execute(type)
        }
    }


}