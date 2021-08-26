package com.devdays.bloodcare.ui.signin

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devdays.bloodcare.util.Event

class SignInViewModel : ViewModel() {
    val mSignInEmailId: ObservableField<String> = ObservableField("")
    val mSignInPassword: ObservableField<String> = ObservableField("")

    private val _signInEvent = MutableLiveData<Event<Unit>>()
    val mSignInEvent: LiveData<Event<Unit>> = _signInEvent

    private val _signInForgotPasswordEvent = MutableLiveData<Event<Unit>>()
    val mSignInForgotPasswordEvent: LiveData<Event<Unit>> = _signInForgotPasswordEvent

    private val _signInSignUpEvent = MutableLiveData<Event<Unit>>()
    val mSignInSignUpEvent: LiveData<Event<Unit>> = _signInSignUpEvent

    fun onClickSignIn() {
        _signInEvent.value = Event(Unit)
    }

    fun onClickForgotPassword() {
        _signInForgotPasswordEvent.value = Event(Unit)
    }

    fun onClickSignInSignUp() {
        _signInSignUpEvent.value = Event(Unit)
    }
}