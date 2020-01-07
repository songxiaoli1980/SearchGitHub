package com.example.searchgithub

import android.app.Application


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        VolleyService.initialize(this)
    }
}