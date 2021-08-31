package com.devdays.bloodcare.ui.forgotpassword

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devdays.bloodcare.R
import com.devdays.bloodcare.util.Event

class ForgotPasswordViewModel : ViewModel() {
    val mForgotPasswordEmailId: ObservableField<String> = ObservableField("")

    private val _forgotPasswordSendOTPEvent = MutableLiveData<Event<Unit>>()
    val mForgotPasswordSendOTPEvent: LiveData<Event<Unit>> = _forgotPasswordSendOTPEvent

    private val _forgotPasswordShowSnackbarMessage = MutableLiveData<Event<Int>>()
    val mForgotPasswordShowSnackbarMessage: LiveData<Event<Int>> =
        _forgotPasswordShowSnackbarMessage

    fun showForgotPasswordSnackbar(mMessage: Int) {
        _forgotPasswordShowSnackbarMessage.value = Event(mMessage)
    }

    fun onClickForgotPasswordSendOTP() {
        when {
            mForgotPasswordEmailId.get()?.isEmpty() == true -> {
                showForgotPasswordSnackbar(R.string.text_error_field_empty)
            }
            else -> {
                _forgotPasswordSendOTPEvent.value = Event(Unit)
            }
        }
    }
}