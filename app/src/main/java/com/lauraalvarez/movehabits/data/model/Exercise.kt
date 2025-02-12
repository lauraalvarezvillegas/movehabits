package com.lauraalvarez.movehabits.data.model

import com.google.firebase.firestore.PropertyName
import com.lauraalvarez.movehabits.data.enums.ExerciseClassification
import com.lauraalvarez.movehabits.data.enums.ExerciseType
import kotlinx.serialization.Serializable

@Serializable
data class Exercise(
    var exerciseId: Int = 0,

    @get:PropertyName("nombre") @set:PropertyName("nombre")
    var exercisename: String = "",

    @get:PropertyName("tipo") @set:PropertyName("tipo")
    var type: String = "",

    @get:PropertyName("clasificacion") @set:PropertyName("clasificacion")
    var classification: String = "",

    @get:PropertyName("img") @set:PropertyName("img")
    var img: String = ""
) {

    constructor() : this(
        exerciseId = 0,
        exercisename = "",
        type = "",
        classification = ""
    )


}