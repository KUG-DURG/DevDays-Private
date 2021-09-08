package com.devdays.bloodcare.util

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.devdays.bloodcare.R
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

@BindingAdapter("spannableString")
fun onSpanString(mTextView: TextView, mData: String) {
    val mWordToSpan = SpannableString(mData)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        mWordToSpan.setSpan(
            ForegroundColorSpan(mTextView.context.resources.getColor(R.color.redDark, null)),
            23,
            30,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        mWordToSpan.setSpan(
            StyleSpan(Typeface.BOLD),
            23,
            30,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }

    mTextView.text = mWordToSpan
}

@BindingAdapter("loadImageById")
fun onLoadImage(mAppCompatImageView: AppCompatImageView, mImageId: Int) {
    Glide.with(mAppCompatImageView.context)
        .load(mImageId)
        .into(mAppCompatImageView)
}

@BindingAdapter("loadImageByUrl")
fun onLoadImageByUrl(mAppCompatImageView: AppCompatImageView, mImageUrl: String) {
    Glide.with(mAppCompatImageView.context)
        .load(mImageUrl)
        .into(mAppCompatImageView)
}