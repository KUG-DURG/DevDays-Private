package com.devdays.bloodcare.util

import androidx.lifecycle.Observer

open class Event<out T>(private val mContent: T) {
    var mHasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (mHasBeenHandled) {
            null
        } else {
            mHasBeenHandled = true
            mContent
        }
    }

    fun peekCount(): T = mContent
}

class EventObserver<T>(private val onEventUnHandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(mEvent: Event<T>?) {
        mEvent?.getContentIfNotHandled()?.let {
            onEventUnHandledContent(it)
        }
    }
}