package com.claudecodeui.android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ClaudeCodeUIApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        // Initialize any application-wide configurations here
    }
}