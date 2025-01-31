package com.lauraalvarez.movehabits.utils

import com.google.firebase.Timestamp
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

object DateTimeUtils {

    fun formatFirebaseDate(timestamp: Timestamp): String {
        val dateTime = DateTime(timestamp.toDate())
        val formatter = DateTimeFormat.forPattern("dd/MM/yy")
        return dateTime.toString(formatter)
    }

    fun formatFirebaseTime(timestamp: Timestamp): String {
        val dateTime = DateTime(timestamp.toDate())
        val formatter = DateTimeFormat.forPattern("HH:mm")
        return dateTime.toString(formatter)
    }
}
