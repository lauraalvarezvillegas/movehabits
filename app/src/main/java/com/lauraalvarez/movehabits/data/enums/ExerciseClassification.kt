package com.lauraalvarez.movehabits.data.enums

import android.content.Context
import com.lauraalvarez.movehabits.R

enum class ExerciseClassification(private val stringResId: Int) {
    UPPER_BODY(R.string.classification_upper_body),
    LOWER_BODY(R.string.classification_lower_body),
    NONE(R.string.classification_none);

    fun getDisplayName(context: Context): String {
        return context.getString(stringResId)
    }
}
