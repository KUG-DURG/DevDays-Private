package com.devdays.bloodcare.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class BloodCareViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                else -> {
                    throw IllegalArgumentException("Unknown ViewModel Class: ${modelClass.name}")
                }
            }
        } as T
}