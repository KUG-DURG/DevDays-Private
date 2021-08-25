package com.devdays.bloodcare.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devdays.bloodcare.ui.find.FindViewModel
import com.devdays.bloodcare.ui.forgotpassword.ForgotPasswordViewModel
import com.devdays.bloodcare.ui.home.HomeViewModel
import com.devdays.bloodcare.ui.profile.ProfileViewModel
import com.devdays.bloodcare.ui.signin.SignInViewModel
import com.devdays.bloodcare.ui.signup.SignUpViewModel

@Suppress("UNCHECKED_CAST")
class BloodCareViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel()
                isAssignableFrom(FindViewModel::class.java) -> FindViewModel()
                isAssignableFrom(ProfileViewModel::class.java) -> ProfileViewModel()
                isAssignableFrom(SignInViewModel::class.java) -> SignInViewModel()
                isAssignableFrom(SignUpViewModel::class.java) -> SignUpViewModel()
                isAssignableFrom(ForgotPasswordViewModel::class.java) -> ForgotPasswordViewModel()
                else -> {
                    throw IllegalArgumentException("Unknown ViewModel Class: ${modelClass.name}")
                }
            }
        } as T
}