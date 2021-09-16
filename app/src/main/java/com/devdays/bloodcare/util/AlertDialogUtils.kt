package com.devdays.bloodcare.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.devdays.bloodcare.R

class AlertDialogUtils {

    private lateinit var mAlertDialogBuilder: AlertDialog.Builder
    private lateinit var mAlertDialog: AlertDialog
    private lateinit var mBackgroundPositiveButton: Button
    private lateinit var mBackgroundNegativeButton: Button

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var ALERT_DIALOG_INSTANCE: AlertDialogUtils? = null

        fun getInstance(): AlertDialogUtils {
            synchronized(this@Companion) {
                var mAlertDialogUtilsInstance = ALERT_DIALOG_INSTANCE
                if (mAlertDialogUtilsInstance == null) {
                    mAlertDialogUtilsInstance = AlertDialogUtils()
                }
                return mAlertDialogUtilsInstance
            }
        }
    }

    fun showAlert(
        mContext: Context,
        mIcon: Int,
        mTitle: String,
        mMessage: String,
        mPositiveButtonText: String,
        positiveOnClickListener: DialogInterface.OnClickListener?,
        onDismissListener: DialogInterface.OnDismissListener?
    ) {
        mAlertDialogBuilder = AlertDialog.Builder(mContext, R.style.AlertDialogTheme)
            .setTitle(mTitle)
            .setMessage(mMessage)
            .setCancelable(false)
            .setPositiveButton(mPositiveButtonText, positiveOnClickListener)
            .setOnDismissListener(onDismissListener)
            .setIcon(mIcon)
        mAlertDialog = mAlertDialogBuilder.create()
        mAlertDialog.show()

        mBackgroundPositiveButton = mAlertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
        mBackgroundPositiveButton.setBackgroundColor(mContext.resources.getColor(android.R.color.transparent))
    }

    fun showAlert(
        mContext: Context,
        mIcon: Int,
        mTitle: String,
        mMessage: String,
        mPositiveButtonText: String,
        positiveOnClickListener: DialogInterface.OnClickListener?,
        mNegativeButtonText: String,
        mNegativeClickListener: DialogInterface.OnClickListener?
    ) {
        mAlertDialogBuilder = AlertDialog.Builder(mContext, R.style.AlertDialogTheme)
            .setTitle(mTitle)
            .setMessage(mMessage)
            .setCancelable(false)
            .setPositiveButton(mPositiveButtonText, positiveOnClickListener)
            .setNegativeButton(mNegativeButtonText, mNegativeClickListener)
            .setIcon(mIcon)
        mAlertDialog = mAlertDialogBuilder.create()
        mAlertDialog.show()

        mBackgroundPositiveButton = mAlertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
        mBackgroundPositiveButton.setTextColor(mContext.resources.getColor(R.color.black))
        mBackgroundPositiveButton.setBackgroundColor(mContext.resources.getColor(android.R.color.transparent))

        mBackgroundNegativeButton = mAlertDialog.getButton(DialogInterface.BUTTON_NEGATIVE)
        mBackgroundNegativeButton.setTextColor(mContext.resources.getColor(R.color.black))
        mBackgroundNegativeButton.setBackgroundColor(mContext.resources.getColor(android.R.color.transparent))
    }
}