package com.lauraalvarez.movehabits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.lauraalvarez.movehabits.ui.navigation.NavigationWrapper
import com.lauraalvarez.movehabits.ui.theme.MoveHabitsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoveHabitsTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    NavigationWrapper()
                }
            }
        }
    }
}


