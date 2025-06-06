package com.example.mabesogne.data.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mabesogne.data.user.User
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("SELECT * FROM user WHERE email = :email AND password = :password LIMIT 1")
    suspend fun getUser(email: String, password: String): User?

    @Query("SELECT EXISTS(SELECT 1 FROM user LIMIT 1)")
    fun isLoggedIn(): Flow<Boolean>

    @Delete
    suspend fun delete(user: User)

    @Update
    suspend fun update(user: User)
}