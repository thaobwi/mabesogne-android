package com.example.mabesogne.data.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int =0,
    val username: String,
    val email: String,
    val password: String,
)