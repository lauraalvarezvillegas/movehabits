package com.lauraalvarez.movehabits.data.model

import com.lauraalvarez.movehabits.data.enums.ExerciseClassification
import com.lauraalvarez.movehabits.data.enums.ExerciseType

data class Exercise(
    var exerciseId: Int = 0,
    var exercisename: String = "",
    var type: ExerciseType,
    var classification: ExerciseClassification = ExerciseClassification.NONE,
    var img: String = ""
) {
    constructor() : this(
        exerciseId = 0,
        exercisename = "",
        type = ExerciseType.STRENGTH,
        classification = ExerciseClassification.NONE,
        img = ""
    )
}

