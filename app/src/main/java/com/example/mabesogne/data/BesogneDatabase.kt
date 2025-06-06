package com.example.mabesogne.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mabesogne.data.task.Task
import com.example.mabesogne.data.task.TaskDao
import com.example.mabesogne.data.user.User
import com.example.mabesogne.data.user.UserDao

@Database(entities = [Task::class, User::class], version = 1)
abstract class BesogneDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: BesogneDatabase? = null

        fun getDatabase(context: Context): BesogneDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BesogneDatabase::class.java,
                    "besogne_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}