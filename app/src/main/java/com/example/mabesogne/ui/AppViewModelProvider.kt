package com.example.mabesogne.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.example.mabesogne.data.TaskDatabase
import com.example.mabesogne.repository.TaskRepository
import com.example.mabesogne.viewmodel.TaskViewModelFactory

object AppViewModelProvider {
    lateinit var Factory: ViewModelProvider.Factory

    fun initialize(application: Application) {
        val db = TaskDatabase.getDatabase(application)
        val repo = TaskRepository(db.taskDao())
        Factory = TaskViewModelFactory(repo)
    }
}