package com.devdays.bloodcare.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devdays.bloodcare.ui.find.FindViewModel
import com.devdays.bloodcare.ui.home.HomeViewModel

@Suppress("UNCHECKED_CAST")
class BloodCareViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel()
                isAssignableFrom(FindViewModel::class.java) -> FindViewModel()
                else -> {
                    throw IllegalArgumentException("Unknown ViewModel Class: ${modelClass.name}")
                }
            }
        } as T
}