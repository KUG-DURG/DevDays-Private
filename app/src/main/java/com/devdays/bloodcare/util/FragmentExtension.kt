package com.devdays.bloodcare.util

import androidx.fragment.app.Fragment
import com.devdays.bloodcare.ui.BloodCareApplication
import com.devdays.bloodcare.ui.BloodCareViewModelFactory

fun Fragment.getViewModelFactory(): BloodCareViewModelFactory {
    val mRepository = (requireContext().applicationContext as BloodCareApplication)
    return BloodCareViewModelFactory()
}