package com.example.mabesogne

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mabesogne.ui.AppViewModelProvider
import com.example.mabesogne.ui.screens.LoginScreen
import com.example.mabesogne.ui.screens.SignupScreen
import com.example.mabesogne.ui.screens.TaskListScreen
import com.example.mabesogne.ui.theme.MaBesogneTheme
import com.example.mabesogne.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaBesogneTheme {
                val navController = rememberNavController()
                val authViewModel: AuthViewModel = viewModel(factory = AppViewModelProvider.Factory)

                val isLoggedIn by authViewModel.isLoggedIn.collectAsState()
                val authState by authViewModel.authState.collectAsState()

                LaunchedEffect(isLoggedIn) {
                    navController.navigate(if (isLoggedIn) "tasks" else "login") {
                        popUpTo(0)
                    }
                }

                NavHost(navController, startDestination = "splash") {
                    composable("splash") {
                        val isLoggedIn by authViewModel.isLoggedIn.collectAsState()

                        LaunchedEffect(isLoggedIn) {
                            navController.navigate(if (isLoggedIn) "tasks" else "login") {
                                popUpTo("splash") { inclusive = true }
                            }
                        }
                    }
                    composable("login") {
                        LoginScreen(
                            onNavigateToSignUp = { navController.navigate("signup") },
                            onLoginSuccess = {
                                navController.navigate("tasks"){
//                                    popUpTo("login") { inclusive = true }
                                }
                            },
                            viewModel = authViewModel
                        )
                    }
                    composable("signup") {
                        SignupScreen(
                            onNavigateBack = { navController.popBackStack("login", inclusive = false) },
                            onSignupSuccess = {
                                navController.navigate("login") {
                                    popUpTo("signup") { inclusive = true }
                                }
                            },
                            viewModel = authViewModel
                            )
                    }
                    composable("tasks") {
                        TaskListScreen()
                    }

                }
            }
        }
    }
}

