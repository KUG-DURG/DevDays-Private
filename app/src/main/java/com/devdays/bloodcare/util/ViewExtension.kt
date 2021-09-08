package com.devdays.bloodcare.util

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

fun View.toast(mContext: Context, mMessage: String, mTimeLength: Int) {
    Toast.makeText(mContext, mMessage, mTimeLength).show()
}

fun View.showSnackbar(mSnackbarText: String, mTimeLength: Int) {
    val mSnackbar = Snackbar.make(this@showSnackbar, mSnackbarText, mTimeLength)
    val mView = mSnackbar.view
    val mParams = mView.layoutParams as CoordinatorLayout.LayoutParams
    mParams.gravity = Gravity.BOTTOM
    mView.layoutParams = mParams
    mSnackbar.animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE
    mSnackbar.run {
        addCallback(object : Snackbar.Callback() {
            override fun onShown(sb: Snackbar?) {
//                EspressoIdlingResource.increment()
            }

            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
//                EspressoIdlingResource.decrement()
            }
        })
        show()
    }
}

fun View.setUpSnackbar(
    mLifecycleOwner: LifecycleOwner,
    mSnackbarEvent: LiveData<Event<Int>>,
    mTimeLength: Int
) {
    mSnackbarEvent.observe(mLifecycleOwner, Observer { mEvent ->
        mEvent.getContentIfNotHandled()?.let {
            showSnackbar(context.getString(it), mTimeLength)
        }
    })
}