package com.example.mabesogne.app

import android.app.Application
import com.example.mabesogne.ui.AppViewModelProvider

class QuickTaskApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppViewModelProvider.initialize(this)
    }
}