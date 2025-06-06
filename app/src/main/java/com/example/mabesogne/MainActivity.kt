package com.example.mabesogne

import android.app.Activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mabesogne.ui.screens.LoginScreen
import com.example.mabesogne.ui.screens.SignupScreen
import com.example.mabesogne.ui.screens.TaskListScreen
import com.example.mabesogne.viewmodel.TaskViewModel
import com.example.mabesogne.ui.theme.MaBesogneTheme
import com.example.mabesogne.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaBesogneTheme {
                val navController = rememberNavController()
                val authViewModel: AuthViewModel = viewModel()

                val isLoggedIn by authViewModel.isLoggedIn.collectAsState()

                LaunchedEffect(isLoggedIn) {
                    navController.navigate(if (isLoggedIn) "tasks" else "login") {
                        popUpTo(0)
                    }
                }

                NavHost(navController, startDestination = "splash") {
                    composable("splash") { /* Empty, handled by LaunchedEffect */ }
                    composable("login") { LoginScreen() }
                    composable("signup") { SignupScreen() }
                    composable("tasks") { TaskListScreen() }
                }
            }
        }
    }
}
