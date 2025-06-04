package com.example.mabesogne.data

import android.icu.text.CaseMap.Title
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int =0,
    val title: String,
    val description: String,
    val isDone: Boolean = false,
    val dueDate: Long? = null
)