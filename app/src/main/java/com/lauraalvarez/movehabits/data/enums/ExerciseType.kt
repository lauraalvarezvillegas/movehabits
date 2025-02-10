package com.lauraalvarez.movehabits.data.enums

import android.content.Context
import com.lauraalvarez.movehabits.R
import kotlinx.serialization.Serializable

@Serializable
enum class ExerciseType(private val stringResId: Int) {
    STRENGTH(R.string.exercise_type_strength),
    CARDIO(R.string.exercise_type_cardio);

    fun getDisplayName(context: Context): String {
        return context.getString(stringResId)
    }
}