package com.example.mabesogne.repository


import com.example.mabesogne.data.user.User
import com.example.mabesogne.data.user.UserDao
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): Boolean
    suspend fun signup(username: String, email: String, password: String): Boolean
    fun isLoggedIn(): Flow<Boolean>
}

class AuthRepositoryImpl(private val authDao: UserDao) : AuthRepository {
    override suspend fun login(email: String, password: String): Boolean {
        return authDao.getUser(email, password) != null
    }

    override suspend fun signup(username: String, email: String, password: String): Boolean {
        authDao.insert(User(username = username, email = email, password = password))
        return true
    }

    override fun isLoggedIn(): Flow<Boolean> = authDao.isLoggedIn()
}