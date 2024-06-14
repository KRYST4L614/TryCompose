package com.example.compose

import android.app.Application
import com.example.compose.di.DaggerAppComponent

class App : Application() {
    val appComponent by lazy {
        DaggerAppComponent.builder().context(this).build()
    }
}