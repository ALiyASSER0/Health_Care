package com.example.hospital

import android.app.Application
import com.example.hospital.UI.MySharedPreferences
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class App  : Application() {
    override fun onCreate() {
        super.onCreate()

        MySharedPreferences.init(this)
    }
}