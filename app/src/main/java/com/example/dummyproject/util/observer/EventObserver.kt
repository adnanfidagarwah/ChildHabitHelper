package com.eshaafi.patient.util.observer

import androidx.lifecycle.Observer

class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<ClickEvent<T>> {
 override fun onChanged(clickEvent: ClickEvent<T>?) {
     clickEvent?.getContentIfNotHandled()?.let { value ->
         onEventUnhandledContent(value)
     }
 }
}