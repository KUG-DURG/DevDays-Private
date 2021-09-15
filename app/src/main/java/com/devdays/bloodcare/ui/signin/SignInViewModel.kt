package com.devdays.bloodcare.ui.signin

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devdays.bloodcare.R
import com.devdays.bloodcare.util.Event

class SignInViewModel : ViewModel() {
    val mSignInEmailId: ObservableField<String> = ObservableField("")
    val mSignInPassword: ObservableField<String> = ObservableField("")

    private val _signInEvent = MutableLiveData<Event<Unit>>()
    val mSignInEvent: LiveData<Event<Unit>> = _signInEvent

    private val _signInSnackbarMessage = MutableLiveData<Event<Int>>()
    val mSignInSnackbarMessage: LiveData<Event<Int>> = _signInSnackbarMessage

    private val _signInSignUpEvent = MutableLiveData<Event<Unit>>()
    val mSignInSignUpEvent: LiveData<Event<Unit>> = _signInSignUpEvent

    private fun showSnackbar(mMessage: Int) {
        _signInSnackbarMessage.value = Event(mMessage)
    }

    fun onClickSignIn(mView: View) {
        when {
            mSignInEmailId.get()?.isEmpty() == true -> {
                showSnackbar(R.string.text_error_field_empty)
            }
            mSignInPassword.get()?.isEmpty() == true -> {
                showSnackbar(R.string.text_error_field_empty)
            }
            mSignInPassword.get()?.length!! <= 6 -> {
                showSnackbar(R.string.text_error_password)
            }
            else -> {
                _signInEvent.value = Event(Unit)
            }
        }
    }

    fun onClickSignInSignUp() {
        _signInSignUpEvent.value = Event(Unit)
    }
}