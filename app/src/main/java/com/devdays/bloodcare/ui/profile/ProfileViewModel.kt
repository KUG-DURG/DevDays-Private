package com.devdays.bloodcare.ui.profile

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {
    val mProfileFullName: ObservableField<String> = ObservableField("")
    val mProfileEmailId: ObservableField<String> = ObservableField("")
    val mProfileMobileNumber: ObservableField<String> = ObservableField("")
    val mProfilePassword: ObservableField<String> = ObservableField("")
    val mProfileLocation: ObservableField<String> = ObservableField("")
    val mProfileBloodGroup: ObservableField<String> = ObservableField("")
}