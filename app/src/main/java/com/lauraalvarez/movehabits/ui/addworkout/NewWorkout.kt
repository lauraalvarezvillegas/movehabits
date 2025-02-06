package com.lauraalvarez.movehabits.ui.addworkout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lauraalvarez.movehabits.R
import com.lauraalvarez.movehabits.data.enums.ExerciseType
import org.joda.time.LocalDate
import org.joda.time.LocalTime
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.google.firebase.Timestamp
import com.lauraalvarez.movehabits.data.model.WorkoutExercise
import com.lauraalvarez.movehabits.ui.layout.MoveHabitsButton
import com.lauraalvarez.movehabits.ui.widgets.DatePickerMaterialTheme
import com.lauraalvarez.movehabits.ui.widgets.TimePickerMaterialTheme
import com.lauraalvarez.movehabits.ui.workouts.ExerciseAtWorkoutItem
import com.lauraalvarez.movehabits.utils.DateTimeUtils
import org.joda.time.DateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewWorkoutScreen(selectedWorkoutType: ExerciseType) {
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    var selectedTime by remember { mutableStateOf<LocalTime?>(null) }


    var showDatePickerDialog by remember { mutableStateOf(false) }
    var showTimePickerDialog by remember { mutableStateOf(false) }


    val currentYear = DateTime.now().year
    val todayMillis = DateTime().withTimeAtStartOfDay().millis

    var exercises by remember { mutableStateOf<List<WorkoutExercise>>(emptyList()) }



    val datePickerState = rememberDatePickerState(
        initialDisplayedMonthMillis = todayMillis,
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                val selectedDate = DateTime(utcTimeMillis)
                val today = DateTime()

                return when {
                    selectedDate.year > today.year -> true
                    selectedDate.year == today.year -> {
                        selectedDate.monthOfYear > today.monthOfYear ||
                                (selectedDate.monthOfYear == today.monthOfYear && selectedDate.dayOfMonth >= today.dayOfMonth)
                    }

                    else -> false
                }
            }


            override fun isSelectableYear(year: Int): Boolean {
                return year >= currentYear
            }
        }
    )


    val today = LocalDate.now()
    val now = LocalTime.now()
    val timePickerState = rememberTimePickerState(
        initialHour = now.hourOfDay,
        initialMinute = now.minuteOfHour,
        is24Hour = true
    )

    fun isSelectableTime(hour: Int, minute: Int): Boolean {
        return if (selectedDate == LocalDate.now()) {
            // If the date is today, the time must be greater than or equal to the current time
            hour > now.hourOfDay || (hour == now.hourOfDay && minute >= now.minuteOfHour)
        } else {
            true
        }
    }


    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBar(
                onCloseClick = { /*TODO:  Lógica para cerrar pantalla */ },
                selectedWorkoutType = selectedWorkoutType
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                onClick = { showDatePickerDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painterResource(id = R.drawable.date_icon),
                        contentDescription = stringResource(R.string.select_date),
                        modifier = Modifier.padding(end = 5.dp)
                    )
                    Text(
                        text = selectedDate?.let {
                            DateTimeUtils.formatFirebaseDate(Timestamp(it.toDate()))
                        } ?: stringResource(R.string.select_date),
                        modifier = Modifier.padding(vertical = 6.dp)
                    )
                }
            }

            Card(
                onClick = {
                    if (selectedDate != null) {
                        showTimePickerDialog = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .alpha(if (selectedDate == null) 0.5f else 1f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painterResource(id = R.drawable.time_icon),
                        contentDescription = stringResource(R.string.select_time),
                        modifier = Modifier.padding(end = 5.dp)
                    )
                    Text(
                        text = selectedTime?.let {
                            DateTimeUtils.formatFirebaseTime(
                                Timestamp(
                                    it.toDateTimeToday().toDate()
                                )
                            )
                        } ?: stringResource(R.string.select_time),
                        modifier = Modifier.padding(vertical = 6.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            //exercise list

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.Left
            ) {
                Text(
                    text = stringResource(R.string.exercises_text),
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 20.sp,
                    color = colorResource(R.color.original_blue),
                    modifier = Modifier.padding(start = 35.dp, top = 14.dp)
                )

                /*TODO: pasar parametro de workoutexercise

                MoveHabitsButton(
                    Modifier
                        .width(190.dp)
                        .height(50.dp)
                        .padding(start = 55.dp),
                    stringResource(R.string.add_text),
                    true,
                    onAddExerciseClicked(workoutExercise)
                )*/


            }

            LazyColumn(
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 16.dp)
                    .fillMaxWidth()
            ) {
                items(exercises) {exercise->
                    ExerciseAtWorkoutItem(exercise, {})

                }

            }
        }
    }

    if (showDatePickerDialog) {
        DatePickerDialog(
            onDismissRequest = { showDatePickerDialog = false },
            confirmButton = {
                Button(colors = (ButtonDefaults.textButtonColors(
                    containerColor = colorResource(R.color.original_blue),
                    contentColor = colorResource(R.color.white)
                )
                        ), onClick = {
                    val selectedMillis = datePickerState.selectedDateMillis
                    if (selectedMillis != null) {
                        val selected = LocalDate(datePickerState.selectedDateMillis)

                        if (!selected.isBefore(today) || selected.isEqual(today)) {
                            selectedDate = selected
                            selectedTime = null
                            showDatePickerDialog = false
                        }
                    }
                }) {
                    Text(text = stringResource(R.string.confirm_text))
                }
            }
        ) {
            DatePickerMaterialTheme(datePickerState)
        }
    }


    if (showTimePickerDialog) {
        DatePickerDialog(
            onDismissRequest = { showTimePickerDialog = false },
            confirmButton = {
                val selectedHour = timePickerState.hour
                val selectedMinute = timePickerState.minute
                val isValid = isSelectableTime(selectedHour, selectedMinute)

                Button(
                    colors = (ButtonDefaults.textButtonColors(
                        containerColor = colorResource(R.color.original_blue),
                        contentColor = colorResource(R.color.white)
                    )
                            ),
                    onClick = {
                        if (isValid) {
                            selectedTime = LocalTime(selectedHour, selectedMinute)
                            showTimePickerDialog = false
                        }
                    },
                    enabled = isValid
                ) {
                    Text(text = stringResource(R.string.confirm_text))
                }
            }
        ) {
            TimePickerMaterialTheme(timePickerState)
        }
    }

    fun onAddExerciseClicked(newExercise: WorkoutExercise) {
        exercises = exercises + newExercise
    }


}




@Composable
fun TopBar(onCloseClick: () -> Unit, selectedWorkoutType: ExerciseType) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorResource(R.color.original_blue),
                RoundedCornerShape(bottomStart = 36.dp, bottomEnd = 36.dp)
            )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onCloseClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.close_icon),
                        contentDescription = stringResource(R.string.close_text),
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                }


                Text(
                    text = stringResource(R.string.new_workout_text).uppercase(),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 30.dp)
                )

            }

            Text(
                text = "Tipo: ${selectedWorkoutType.getDisplayName(LocalContext.current)}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp),
                textAlign = TextAlign.Center,
                color = Color.White,
            )

        }


    }
}


@Preview(showBackground = true)
@Composable
fun PreviewNewWorkoutScreen() {
    NewWorkoutScreen(selectedWorkoutType = ExerciseType.STRENGTH)
}


