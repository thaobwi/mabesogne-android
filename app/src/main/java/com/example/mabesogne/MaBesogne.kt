package com.example.mabesogne

import android.app.Application
import com.example.mabesogne.ui.AppViewModelProvider

class MaBesogne : Application() {
    override fun onCreate() {
        super.onCreate()
        AppViewModelProvider.initialize(this)
    }
}