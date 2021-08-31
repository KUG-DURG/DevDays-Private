package com.devdays.bloodcare.ui.signup

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devdays.bloodcare.R
import com.devdays.bloodcare.util.Event

class SignUpViewModel : ViewModel() {
    val mSignUpFullName: ObservableField<String> = ObservableField("")
    val mSignUpEmailId: ObservableField<String> = ObservableField("")
    val mSignUpMobileNumber: ObservableField<String> = ObservableField("")
    val mSignUpPassword: ObservableField<String> = ObservableField("")
    val mSignUpLocation: ObservableField<String> = ObservableField("")
    val mSignUpBloodGroup: ObservableField<String> = ObservableField("")

    private val _signUpEvent = MutableLiveData<Event<Unit>>()
    val mSignUpEvent: LiveData<Event<Unit>> = _signUpEvent

    private val _signUpSnackbarMessage = MutableLiveData<Event<Int>>()
    val mSignUpSnackbarMessage: LiveData<Event<Int>> = _signUpSnackbarMessage

    private val _signUpSignInEvent = MutableLiveData<Event<Unit>>()
    val mSignUpSignInEvent: LiveData<Event<Unit>> = _signUpSignInEvent

    fun showSignUpSnackbar(mMessage: Int) {
        _signUpSnackbarMessage.value = Event(mMessage)
    }

    fun onClickSignUp() {
        when {
            mSignUpFullName.get()?.isEmpty() == true -> {
                showSignUpSnackbar(R.string.text_error_field_empty)
            }
            mSignUpEmailId.get()?.isEmpty() == true -> {
                showSignUpSnackbar(R.string.text_error_field_empty)
            }
            mSignUpMobileNumber.get()?.isEmpty() == true -> {
                showSignUpSnackbar(R.string.text_error_field_empty)
            }
            mSignUpMobileNumber.get()?.length!! < 10 -> {
                showSignUpSnackbar(R.string.text_label_invalid_mobile_number)
            }
            mSignUpPassword.get()?.length!! < 6 -> {
                showSignUpSnackbar(R.string.text_error_password)
            }
            mSignUpLocation.get()?.isEmpty() == true -> {
                showSignUpSnackbar(R.string.text_error_field_empty)
            }
            mSignUpBloodGroup.get()?.isEmpty() == true -> {
                showSignUpSnackbar(R.string.text_error_field_empty)
            }
            else -> {
                _signUpEvent.value = Event(Unit)
            }
        }
    }

    fun onClickSignUpSignIn() {
        _signUpSignInEvent.value = Event(Unit)
    }
}