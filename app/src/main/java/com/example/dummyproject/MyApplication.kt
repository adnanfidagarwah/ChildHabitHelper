package com.example.dummyproject

import android.app.Activity
import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {

    var activity: Activity? = null

    companion object {

        var INSTANCE: MyApplication? = null

        fun get(): MyApplication? {
            return INSTANCE
        }


    }


    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}