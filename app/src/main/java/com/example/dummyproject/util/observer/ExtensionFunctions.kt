package com.eshaafi.patient.util.observer

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.gson.Gson
import java.lang.reflect.Type

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T?) {
            removeObserver(this)
            observer.onChanged(t)
        }
    })
}



inline fun <reified T> T.toJson(): String = Gson().toJson(this)

inline fun <reified T> String.toObject(): T = Gson().fromJson(this, T::class.java)

inline fun <reified T, K, V> Map<K, V>.toObject(): T = Gson().fromJson(this.toJson(), T::class.java)

// and more classic but less used
fun <T> String.toObject(type: Type): T = Gson().fromJson(this, type)