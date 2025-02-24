package com.lauraalvarez.movehabits.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.lauraalvarez.movehabits.data.model.WorkoutExercise
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

@Singleton
class UserPreferences @Inject constructor(@ApplicationContext context: Context) {

    private val dataStore = context.dataStore

    companion object {
        val KEY_IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        val KEY_USER_ID = stringPreferencesKey("user_id")
        val KEY_NEW_EXERCISE_WORKOUT = stringPreferencesKey("new_exercise_workout")
        val KEY_FROM_ADD_EXERCISE = booleanPreferencesKey("from_add_exercise")
    }

    val isLoggedIn: Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[KEY_IS_LOGGED_IN] ?: false
    }

    val userId: Flow<String?> = dataStore.data.map { prefs ->
        prefs[KEY_USER_ID]
    }

    suspend fun saveUser(userId: String) {
        dataStore.edit { prefs ->
            prefs[KEY_IS_LOGGED_IN] = true
            prefs[KEY_USER_ID] = userId
        }
    }

    suspend fun clearUser() {
        dataStore.edit { prefs ->
            prefs[KEY_IS_LOGGED_IN] = false
            prefs.remove(KEY_USER_ID)
        }
    }

    val newExerciseWorkout: Flow<WorkoutExercise?> = dataStore.data.map { prefs ->
        prefs[KEY_NEW_EXERCISE_WORKOUT]?.let { jsonString ->
            try {
                Json.decodeFromString<WorkoutExercise>(jsonString)
            } catch (e: Exception) {
                null // Return null if decoding fails
            }
        }
    }

    suspend fun saveNewExerciseWorkout(exercise: WorkoutExercise) {
        val jsonString = Json.encodeToString(exercise)
        dataStore.edit { prefs ->
            prefs[KEY_NEW_EXERCISE_WORKOUT] = jsonString
        }
    }

    suspend fun clearNewExerciseWorkout() {
        dataStore.edit { prefs ->
            prefs.remove(KEY_NEW_EXERCISE_WORKOUT)
        }
    }

    val fromAddEercise: Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[KEY_FROM_ADD_EXERCISE] ?: false
    }


    suspend fun saveFromAddExercise(fromAddExercise: Boolean) {
        dataStore.edit { prefs ->
            prefs[KEY_FROM_ADD_EXERCISE] = fromAddExercise
        }
    }

    suspend fun getFromAddExercise(): Boolean {
        return dataStore.data.map { prefs -> prefs[KEY_FROM_ADD_EXERCISE] ?: false }.first()
    }



}
