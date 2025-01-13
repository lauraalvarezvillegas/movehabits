package com.lauraalvarez.movehabits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.lauraalvarez.movehabits.ui.login.LoginScreen
import com.lauraalvarez.movehabits.ui.login.LoginViewModel
import com.lauraalvarez.movehabits.ui.navigation.NavigationWrapper
import com.lauraalvarez.movehabits.ui.theme.MoveHabitsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoveHabitsTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    NavigationWrapper()
                }
            }
        }
    }
}

