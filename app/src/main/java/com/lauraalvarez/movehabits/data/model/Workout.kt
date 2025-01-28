package com.lauraalvarez.movehabits.data.model


data class Workout(
    var id: String? = null,
    var idUsuario: String? = null,
    //var fechaHora: Timestamp? = null, // time stamp de firebase
    var duracionTotalSeg: Long = 0L,
    var tipo: String? = null,
    var descansoSeg: Long = 0L,
    var ejercicios: ArrayList<WorkoutExercise> = ArrayList(),
    var completado: Boolean? = null
)
