package com.example.mabesogne.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mabesogne.data.BesogneDatabase
import com.example.mabesogne.repository.AuthRepository
import com.example.mabesogne.repository.AuthRepositoryImpl
import com.example.mabesogne.repository.TaskRepository
import com.example.mabesogne.viewmodel.AuthViewModel
import com.example.mabesogne.viewmodel.TaskViewModel

object AppViewModelProvider {
    lateinit var Factory: ViewModelProvider.Factory

    fun initialize(application: Application) {
        val db = BesogneDatabase.getDatabase(application)
        val taskRepo = TaskRepository(db.taskDao())
        val authRepo = AuthRepositoryImpl(db.userDao())

        Factory = MultiViewModelFactory(authRepo, taskRepo)
    }
}

class MultiViewModelFactory(
    private val authRepo: AuthRepository,
    private val taskRepo: TaskRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> {
                AuthViewModel(authRepo) as T
            }
            modelClass.isAssignableFrom(TaskViewModel::class.java) -> {
                TaskViewModel(taskRepo) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}