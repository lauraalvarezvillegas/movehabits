package com.lauraalvarez.movehabits.data.model

import com.lauraalvarez.movehabits.data.enums.ExerciseClassification
import com.lauraalvarez.movehabits.data.enums.ExerciseType

data class Exercise(
    var exerciseId: Int,
    var exercisename: String,
    var type : ExerciseType,
    var classification : ExerciseClassification,
    var img : String


)
