package com.example.mabesogne.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mabesogne.data.Task
import com.example.mabesogne.ui.AppViewModelProvider
import com.example.mabesogne.viewmodel.TaskViewModel

@Composable
fun TaskListScreen (viewModel:TaskViewModel = viewModel (factory = AppViewModelProvider.Factory)) {
    val tasks = viewModel.tasks.collectAsState().value
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)){
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(tasks.size) {index ->
                TaskCard(task = tasks[index])
            }
        }
        // TODO: add task input form
    }
}

@Composable
fun TaskCard(task: Task) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Column( modifier = Modifier.padding(16.dp)) {
            Text(task.title, style = MaterialTheme.typography.titleMedium)
            Text(task.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

