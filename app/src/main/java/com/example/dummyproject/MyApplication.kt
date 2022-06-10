package com.example.dummyproject

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {

    var activity: Activity? = null

    companion object {

        @SuppressLint("StaticFieldLeak")
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